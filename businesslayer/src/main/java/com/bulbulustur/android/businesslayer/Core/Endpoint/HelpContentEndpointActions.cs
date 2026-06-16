using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetHelpContentListAsync")]
public async Task<Result<List<HelpContentDTO>>> GetHelpContentListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpContentByIdAsync")]
public async Task<Result<HelpContentUpdateModel>> GetHelpContentByIdAsync(
    int helpContentId)
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpContentByIdExtendedAsync")]
public async Task<Result<HelpContentDTO>> GetHelpContentByIdExtendedAsync(
    int helpContentId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] HelpContentInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] HelpContentUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int helpContentId)
{
    throw new NotImplementedException();
}
