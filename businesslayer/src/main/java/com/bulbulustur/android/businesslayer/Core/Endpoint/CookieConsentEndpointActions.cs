using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCookieConsentListAsync")]
public async Task<Result<List<CookieConsentDTO>>> GetCookieConsentListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCookieConsentByIdAsync")]
public async Task<Result<CookieConsentUpdateModel>> GetCookieConsentByIdAsync(
    int cookieConsentId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCookieConsentByIdExtendedAsync")]
public async Task<Result<CookieConsentDTO>> GetCookieConsentByIdExtendedAsync(
    int cookieConsentId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CookieConsentInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CookieConsentUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int cookieConsentId)
{
    throw new NotImplementedException();
}
