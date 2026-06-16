using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetHelpProjectHelpMapListAsync")]
public async Task<Result<List<HelpProjectHelpMapDTO>>> GetHelpProjectHelpMapListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpProjectHelpMapByIdAsync")]
public async Task<Result<HelpProjectHelpMapUpdateModel>> GetHelpProjectHelpMapByIdAsync(
    int helpProjectHelpMapId)
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpProjectHelpMapByIdExtendedAsync")]
public async Task<Result<HelpProjectHelpMapDTO>> GetHelpProjectHelpMapByIdExtendedAsync(
    int helpProjectHelpMapId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] HelpProjectHelpMapInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] HelpProjectHelpMapUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int helpProjectHelpMapId)
{
    throw new NotImplementedException();
}
