using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetHelpListAsync")]
public async Task<Result<List<HelpDTO>>> GetHelpListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpByIdAsync")]
public async Task<Result<HelpUpdateModel>> GetHelpByIdAsync(
    int helpId)
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpByIdExtendedAsync")]
public async Task<Result<HelpDTO>> GetHelpByIdExtendedAsync(
    int helpId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] HelpInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] HelpUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int helpId)
{
    throw new NotImplementedException();
}
