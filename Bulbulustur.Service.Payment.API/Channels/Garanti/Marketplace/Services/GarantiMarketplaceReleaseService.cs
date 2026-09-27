using Bulbulustur.Service.Payment.API.Channels.Garanti.Common.Interfaces;
using Bulbulustur.Service.Payment.API.Channels.Garanti.Common.Models;
using Bulbulustur.Service.Payment.API.Channels.Garanti.Marketplace.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace Bulbulustur.Service.Payment.API.Channels.Garanti.Marketplace.Services
{
    public class GarantiMarketplaceReleaseService
    {
        private const string ReleaseEndpoint = "/api/marketplace/release/commit";
        private const string CancelReleaseEndpoint = "/api/marketplace/release/cancel";

        private readonly IGarantiHttpClient _httpClient;
        private readonly IGarantiCredentialProvider _credentialProvider;
        private readonly IGarantiReturnCodeMapper _returnCodeMapper;

        public GarantiMarketplaceReleaseService(IGarantiHttpClient httpClient, IGarantiCredentialProvider credentialProvider, IGarantiReturnCodeMapper returnCodeMapper)
        {
            _httpClient = httpClient;
            _credentialProvider = credentialProvider;
            _returnCodeMapper = returnCodeMapper;
        }

        public Task<GarantiMarketplaceReleaseResult> ReleaseAsync(GarantiMarketplaceReleaseCommand command, CancellationToken cancellationToken = default)
        {
            return ExecuteAsync(ReleaseEndpoint, "RELEASE", command, cancellationToken);
        }

        public Task<GarantiMarketplaceReleaseResult> CancelReleaseAsync(GarantiMarketplaceReleaseCommand command, CancellationToken cancellationToken = default)
        {
            return ExecuteAsync(CancelReleaseEndpoint, "CANCEL_RELEASE", command, cancellationToken);
        }

        private async Task<GarantiMarketplaceReleaseResult> ExecuteAsync(string endpoint, string operation, GarantiMarketplaceReleaseCommand command, CancellationToken cancellationToken)
        {
            GarantiMarketplaceReleaseResult validation = Validate(command, operation);
            if (validation != null)
            {
                return validation;
            }

            GarantiCredentials credentials = await _credentialProvider.GetAsync(cancellationToken);

            if (string.IsNullOrWhiteSpace(credentials.MarketplaceMerchantId))
            {
                return Failed($"GARANTI_{operation}_MARKETPLACE_MERCHANT_ID_MISSING", "Garanti MarketplaceMerchantId credential is missing.");
            }

            GarantiReleaseRequest request = new GarantiReleaseRequest
            {
                MarketplaceMerchantId = credentials.MarketplaceMerchantId,
                ReleaseTxnList = command.Items.Select(x => new GarantiReleaseTxn
                {
                    SubMerchantNum = x.SubMerchantNum,
                    SubTxnId = x.SubTxnId,
                    OrderId = x.OrderId
                }).ToList()
            };

            GarantiHttpResult<GarantiReleaseResponse> httpResult =
                await _httpClient.PostAsync<GarantiReleaseRequest, GarantiReleaseResponse>(endpoint, request, cancellationToken: cancellationToken);

            if (httpResult.TimedOut)
            {
                return Unknown(httpResult.ProviderRequestId, $"GARANTI_{operation}_TIMEOUT", httpResult.ErrorMessage);
            }

            if (!httpResult.TransportSuccess || httpResult.Response == null)
            {
                return Unknown(httpResult.ProviderRequestId, $"GARANTI_{operation}_TRANSPORT_UNKNOWN", httpResult.ErrorMessage);
            }

            if (!httpResult.ResponseHashValid)
            {
                return Unknown(httpResult.ProviderRequestId, $"GARANTI_{operation}_RESPONSE_HASH_INVALID", httpResult.ErrorMessage);
            }

            GarantiReturnCodeResult mapped = _returnCodeMapper.Map(httpResult.Response);

            if (!mapped.Success)
            {
                return Failed($"GARANTI_{operation}_{mapped.ReturnCode}_{mapped.ReasonCode}", mapped.Message, httpResult.ProviderRequestId, httpResult.Response);
            }

            List<GarantiMarketplaceReleaseItemResult> items = httpResult.Response.ReleaseTxnList
                .Select(x => new GarantiMarketplaceReleaseItemResult
                {
                    SubMerchantNum = x.SubMerchantNum,
                    SubTxnId = x.SubTxnId,
                    OrderId = x.OrderId,
                    ReleaseStatus = x.ReleaseStatus,
                    ResponseMessage = x.ResponseMessage
                })
                .ToList();

            bool success = items.Count == command.Items.Count &&
                           items.All(x => string.Equals(x.ReleaseStatus, "00", StringComparison.OrdinalIgnoreCase));

            return new GarantiMarketplaceReleaseResult
            {
                Success = success,
                ProviderRequestId = httpResult.ProviderRequestId,
                ErrorCode = success ? null : $"GARANTI_{operation}_ITEM_FAILED",
                Message = success ? mapped.Message : "One or more marketplace release items failed.",
                Items = items
            };
        }

        private static GarantiMarketplaceReleaseResult Validate(GarantiMarketplaceReleaseCommand command, string operation)
        {
            if (command == null || command.Items == null || command.Items.Count == 0)
            {
                return Failed($"GARANTI_{operation}_ITEM_REQUIRED", "At least one release item is required.");
            }

            if (command.Items.Any(x => string.IsNullOrWhiteSpace(x.SubTxnId) || x.SubTxnId.Length > 36))
            {
                return Failed($"GARANTI_{operation}_SUB_TXN_ID_INVALID", "Each SubTxnId is required and cannot exceed 36 characters.");
            }

            if (command.Items.Any(x => !string.IsNullOrWhiteSpace(x.SubMerchantNum) && x.SubMerchantNum.Length > 9))
            {
                return Failed($"GARANTI_{operation}_SUB_MERCHANT_INVALID", "SubMerchantNum cannot exceed 9 characters.");
            }

            if (command.Items.Any(x => !string.IsNullOrWhiteSpace(x.OrderId) && x.OrderId.Length > 16))
            {
                return Failed($"GARANTI_{operation}_ORDER_ID_INVALID", "OrderId cannot exceed 16 characters.");
            }

            if (command.Items.GroupBy(x => x.SubTxnId, StringComparer.OrdinalIgnoreCase).Any(x => x.Count() > 1))
            {
                return Failed($"GARANTI_{operation}_DUPLICATE_SUB_TXN", "The same SubTxnId cannot be sent more than once in a request.");
            }

            return null;
        }

        private static GarantiMarketplaceReleaseResult Failed(string code, string message, string providerRequestId = null, GarantiReleaseResponse response = null)
        {
            return new GarantiMarketplaceReleaseResult
            {
                Success = false,
                ProviderRequestId = providerRequestId,
                ErrorCode = code,
                Message = message,
                Items = response?.ReleaseTxnList?.Select(x => new GarantiMarketplaceReleaseItemResult
                {
                    SubMerchantNum = x.SubMerchantNum,
                    SubTxnId = x.SubTxnId,
                    OrderId = x.OrderId,
                    ReleaseStatus = x.ReleaseStatus,
                    ResponseMessage = x.ResponseMessage
                }).ToList() ?? new List<GarantiMarketplaceReleaseItemResult>()
            };
        }

        private static GarantiMarketplaceReleaseResult Unknown(string providerRequestId, string code, string message)
        {
            return new GarantiMarketplaceReleaseResult
            {
                Success = false,
                RequiresInquiry = true,
                ProviderRequestId = providerRequestId,
                ErrorCode = code,
                Message = message
            };
        }
    }
}
