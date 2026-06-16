using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescCargoDesiPriceListAsync")]
public async Task<Result<List<SystemDescCargoDesiPriceDTO>>> GetSystemDescCargoDesiPriceListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescCargoDesiPriceByIdAsync")]
public async Task<Result<SystemDescCargoDesiPriceUpdateModel>> GetSystemDescCargoDesiPriceByIdAsync(
    int systemDescCargoDesiPriceId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescCargoDesiPriceByIdExtendedAsync")]
public async Task<Result<SystemDescCargoDesiPriceDTO>> GetSystemDescCargoDesiPriceByIdExtendedAsync(
    int systemDescCargoDesiPriceId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescCargoDesiPriceInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescCargoDesiPriceUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescCargoDesiPriceId)
{
    throw new NotImplementedException();
}
