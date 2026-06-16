using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleProductPriceListAsync")]
public async Task<Result<List<WholesaleProductPriceDTO>>> GetWholesaleProductPriceListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductPriceByIdAsync")]
public async Task<Result<WholesaleProductPriceUpdateModel>> GetWholesaleProductPriceByIdAsync(
    int wholesaleProductPriceId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductPriceByIdExtendedAsync")]
public async Task<Result<WholesaleProductPriceDTO>> GetWholesaleProductPriceByIdExtendedAsync(
    int wholesaleProductPriceId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleProductPriceInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleProductPriceUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleProductPriceId)
{
    throw new NotImplementedException();
}
