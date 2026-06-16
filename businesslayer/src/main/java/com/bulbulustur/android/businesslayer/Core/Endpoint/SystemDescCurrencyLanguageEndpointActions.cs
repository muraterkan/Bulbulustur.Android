using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescCurrencyLanguageListAsync")]
public async Task<Result<List<SystemDescCurrencyLanguageDTO>>> GetSystemDescCurrencyLanguageListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescCurrencyLanguageByIdAsync")]
public async Task<Result<SystemDescCurrencyLanguageUpdateModel>> GetSystemDescCurrencyLanguageByIdAsync(
    int systemDescCurrencyLanguageId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescCurrencyLanguageByIdExtendedAsync")]
public async Task<Result<SystemDescCurrencyLanguageDTO>> GetSystemDescCurrencyLanguageByIdExtendedAsync(
    int systemDescCurrencyLanguageId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescCurrencyLanguageInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescCurrencyLanguageUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescCurrencyLanguageId)
{
    throw new NotImplementedException();
}
