using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescYesNoListAsync")]
public async Task<Result<List<SystemDescYesNoDTO>>> GetSystemDescYesNoListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescYesNoByIdAsync")]
public async Task<Result<SystemDescYesNoUpdateModel>> GetSystemDescYesNoByIdAsync(
    int systemDescYesNoId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescYesNoByIdExtendedAsync")]
public async Task<Result<SystemDescYesNoDTO>> GetSystemDescYesNoByIdExtendedAsync(
    int systemDescYesNoId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescYesNoInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescYesNoUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescYesNoId)
{
    throw new NotImplementedException();
}
