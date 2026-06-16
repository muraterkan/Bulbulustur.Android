using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetBuyerRequestListAsync")]
public async Task<Result<List<BuyerRequestDTO>>> GetBuyerRequestListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetBuyerRequestByIdAsync")]
public async Task<Result<BuyerRequestUpdateModel>> GetBuyerRequestByIdAsync(
    int buyerRequestId)
{
    throw new NotImplementedException();
}

[HttpGet("GetBuyerRequestByIdExtendedAsync")]
public async Task<Result<BuyerRequestDTO>> GetBuyerRequestByIdExtendedAsync(
    int buyerRequestId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] BuyerRequestInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] BuyerRequestUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int buyerRequestId)
{
    throw new NotImplementedException();
}
