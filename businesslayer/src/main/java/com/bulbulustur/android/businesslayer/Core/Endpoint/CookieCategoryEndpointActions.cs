using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCookieCategoryListAsync")]
public async Task<Result<List<CookieCategoryDTO>>> GetCookieCategoryListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCookieCategoryByIdAsync")]
public async Task<Result<CookieCategoryUpdateModel>> GetCookieCategoryByIdAsync(
    int cookieCategoryId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCookieCategoryByIdExtendedAsync")]
public async Task<Result<CookieCategoryDTO>> GetCookieCategoryByIdExtendedAsync(
    int cookieCategoryId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CookieCategoryInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CookieCategoryUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int cookieCategoryId)
{
    throw new NotImplementedException();
}
