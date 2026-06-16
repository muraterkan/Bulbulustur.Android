using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleBuyerLastPriceRequestListAsync")]
public async Task<Result<List<WholesaleBuyerLastPriceRequestDTO>>> GetWholesaleBuyerLastPriceRequestListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleBuyerLastPriceRequestByIdAsync")]
public async Task<Result<WholesaleBuyerLastPriceRequestUpdateModel>> GetWholesaleBuyerLastPriceRequestByIdAsync(
    int wholesaleBuyerLastPriceRequestId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleBuyerLastPriceRequestByIdExtendedAsync")]
public async Task<Result<WholesaleBuyerLastPriceRequestDTO>> GetWholesaleBuyerLastPriceRequestByIdExtendedAsync(
    int wholesaleBuyerLastPriceRequestId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleBuyerLastPriceRequestInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleBuyerLastPriceRequestUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleBuyerLastPriceRequestId)
{
    throw new NotImplementedException();
}
