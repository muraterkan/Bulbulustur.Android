using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescLanguageListAsync")]
public async Task<Result<List<SystemDescLanguageDTO>>> GetSystemDescLanguageListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescLanguageByIdAsync")]
public async Task<Result<SystemDescLanguageUpdateModel>> GetSystemDescLanguageByIdAsync(
    int systemDescLanguageId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescLanguageByIdExtendedAsync")]
public async Task<Result<SystemDescLanguageDTO>> GetSystemDescLanguageByIdExtendedAsync(
    int systemDescLanguageId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescLanguageInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescLanguageUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescLanguageId)
{
    throw new NotImplementedException();
}
