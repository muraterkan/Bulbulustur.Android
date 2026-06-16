using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescCurrencyListAsync")]
public async Task<Result<List<SystemDescCurrencyDTO>>> GetSystemDescCurrencyListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescCurrencyByIdAsync")]
public async Task<Result<SystemDescCurrencyUpdateModel>> GetSystemDescCurrencyByIdAsync(
    int systemDescCurrencyId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescCurrencyByIdExtendedAsync")]
public async Task<Result<SystemDescCurrencyDTO>> GetSystemDescCurrencyByIdExtendedAsync(
    int systemDescCurrencyId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescCurrencyInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescCurrencyUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescCurrencyId)
{
    throw new NotImplementedException();
}
