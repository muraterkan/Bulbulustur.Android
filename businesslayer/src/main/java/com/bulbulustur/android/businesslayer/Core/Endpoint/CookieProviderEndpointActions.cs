using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCookieProviderListAsync")]
public async Task<Result<List<CookieProviderDTO>>> GetCookieProviderListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCookieProviderByIdAsync")]
public async Task<Result<CookieProviderUpdateModel>> GetCookieProviderByIdAsync(
    int cookieProviderId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCookieProviderByIdExtendedAsync")]
public async Task<Result<CookieProviderDTO>> GetCookieProviderByIdExtendedAsync(
    int cookieProviderId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CookieProviderInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CookieProviderUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int cookieProviderId)
{
    throw new NotImplementedException();
}
