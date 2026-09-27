using Bulbulustur.Service.Payment.API.Channels.Garanti.Marketplace.Models;
using Bulbulustur.Service.Payment.API.Channels.Garanti.Marketplace.Services;
using Microsoft.AspNetCore.Mvc;
using System.Threading;
using System.Threading.Tasks;

namespace Bulbulustur.Service.Payment.API.Controllers
{
    [Route("api/garanti/marketplace")]
    [ApiController]
    public class GarantiMarketplaceReleaseController : ControllerBase
    {
        private readonly GarantiMarketplaceReleaseService _releaseService;

        public GarantiMarketplaceReleaseController(GarantiMarketplaceReleaseService releaseService)
        {
            _releaseService = releaseService;
        }

        [HttpPost("release")]
        public async Task<IActionResult> Release([FromBody] GarantiMarketplaceReleaseCommand command, CancellationToken cancellationToken)
        {
            GarantiMarketplaceReleaseResult result = await _releaseService.ReleaseAsync(command, cancellationToken);

            if (result.Success) return Ok(result);
            if (result.RequiresInquiry) return Conflict(result);

            return BadRequest(result);
        }

        [HttpPost("release/cancel")]
        public async Task<IActionResult> CancelRelease([FromBody] GarantiMarketplaceReleaseCommand command, CancellationToken cancellationToken)
        {
            GarantiMarketplaceReleaseResult result = await _releaseService.CancelReleaseAsync(command, cancellationToken);

            if (result.Success) return Ok(result);
            if (result.RequiresInquiry) return Conflict(result);

            return BadRequest(result);
        }
    }
}
