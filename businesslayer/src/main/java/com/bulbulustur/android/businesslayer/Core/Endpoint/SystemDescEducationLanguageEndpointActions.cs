using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescEducationLanguageListAsync")]
public async Task<Result<List<SystemDescEducationLanguageDTO>>> GetSystemDescEducationLanguageListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescEducationLanguageByIdAsync")]
public async Task<Result<SystemDescEducationLanguageUpdateModel>> GetSystemDescEducationLanguageByIdAsync(
    int systemDescEducationLanguageId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescEducationLanguageByIdExtendedAsync")]
public async Task<Result<SystemDescEducationLanguageDTO>> GetSystemDescEducationLanguageByIdExtendedAsync(
    int systemDescEducationLanguageId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescEducationLanguageInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescEducationLanguageUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescEducationLanguageId)
{
    throw new NotImplementedException();
}
