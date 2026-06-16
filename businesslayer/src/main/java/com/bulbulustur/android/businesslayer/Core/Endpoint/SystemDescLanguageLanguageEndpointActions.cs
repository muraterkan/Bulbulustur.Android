using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescLanguageLanguageListAsync")]
public async Task<Result<List<SystemDescLanguageLanguageDTO>>> GetSystemDescLanguageLanguageListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescLanguageLanguageByIdAsync")]
public async Task<Result<SystemDescLanguageLanguageUpdateModel>> GetSystemDescLanguageLanguageByIdAsync(
    int systemDescLanguageLanguageId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescLanguageLanguageByIdExtendedAsync")]
public async Task<Result<SystemDescLanguageLanguageDTO>> GetSystemDescLanguageLanguageByIdExtendedAsync(
    int systemDescLanguageLanguageId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescLanguageLanguageInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescLanguageLanguageUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescLanguageLanguageId)
{
    throw new NotImplementedException();
}
