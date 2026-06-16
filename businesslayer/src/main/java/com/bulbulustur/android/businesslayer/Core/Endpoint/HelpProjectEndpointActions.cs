using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetHelpProjectListAsync")]
public async Task<Result<List<HelpProjectDTO>>> GetHelpProjectListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpProjectByIdAsync")]
public async Task<Result<HelpProjectUpdateModel>> GetHelpProjectByIdAsync(
    int helpProjectId)
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpProjectByIdExtendedAsync")]
public async Task<Result<HelpProjectDTO>> GetHelpProjectByIdExtendedAsync(
    int helpProjectId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] HelpProjectInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] HelpProjectUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int helpProjectId)
{
    throw new NotImplementedException();
}
