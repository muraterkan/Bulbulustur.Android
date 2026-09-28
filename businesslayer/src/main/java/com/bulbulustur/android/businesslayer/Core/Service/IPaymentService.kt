package com.bulbulustur.android.businesslayer.Core.Service

import com.bulbulustur.android.businesslayer.Core.Model.RequestModels.GatewayPaymentCreateRequest
import com.bulbulustur.android.businesslayer.Core.Model.ResponseModels.GatewayPaymentCreateResponse
import com.bulbulustur.android.businesslayer.Core.Model.ResponseModels.GatewayPaymentStatusResponse
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IPaymentService {
    suspend fun CreateGatewayPaymentAsync(
        request: GatewayPaymentCreateRequest
    ): Result<GatewayPaymentCreateResponse>

    suspend fun GetGatewayPaymentStatusAsync(
        idempotencyKey: String
    ): Result<GatewayPaymentStatusResponse>
}

class PaymentService(
    private val apiClient: ApiClient = ApiClient
) : IPaymentService {

    override suspend fun CreateGatewayPaymentAsync(
        request: GatewayPaymentCreateRequest
    ): Result<GatewayPaymentCreateResponse> {
        return apiClient.PostRawAsync(
            baseUrl = ApiRoutes.PAYMENT_BASE_URL,
            method = "payment/create",
            data = request
        )
    }

    override suspend fun GetGatewayPaymentStatusAsync(
        idempotencyKey: String
    ): Result<GatewayPaymentStatusResponse> {
        if (idempotencyKey.isBlank()) {
            return Result(
                Success = false,
                Message = "Idempotency key is required."
            )
        }

        return apiClient.GetRawAsync(
            baseUrl = ApiRoutes.PAYMENT_BASE_URL,
            method = "payment/status/$idempotencyKey"
        )
    }
}