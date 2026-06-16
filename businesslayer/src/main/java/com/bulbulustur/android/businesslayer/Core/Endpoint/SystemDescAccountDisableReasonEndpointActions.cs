using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescAccountDisableReasonListAsync")]
public async Task<Result<List<SystemDescAccountDisableReasonDTO>>> GetSystemDescAccountDisableReasonListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescAccountDisableReasonByIdAsync")]
public async Task<Result<SystemDescAccountDisableReasonUpdateModel>> GetSystemDescAccountDisableReasonByIdAsync(
    int systemDescAccountDisableReasonId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescAccountDisableReasonByIdExtendedAsync")]
public async Task<Result<SystemDescAccountDisableReasonDTO>> GetSystemDescAccountDisableReasonByIdExtendedAsync(
    int systemDescAccountDisableReasonId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescAccountDisableReasonInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescAccountDisableReasonUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescAccountDisableReasonId)
{
    throw new NotImplementedException();
}
