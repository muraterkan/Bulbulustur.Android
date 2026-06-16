using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetBasketListAsync")]
public async Task<Result<List<BasketDTO>>> GetBasketListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetBasketByIdAsync")]
public async Task<Result<BasketUpdateModel>> GetBasketByIdAsync(
    int basketId)
{
    throw new NotImplementedException();
}

[HttpGet("GetBasketByIdExtendedAsync")]
public async Task<Result<BasketDTO>> GetBasketByIdExtendedAsync(
    int basketId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] BasketInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] BasketUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int basketId)
{
    throw new NotImplementedException();
}
