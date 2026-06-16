using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescAccountClosureReasonListAsync")]
public async Task<Result<List<SystemDescAccountClosureReasonDTO>>> GetSystemDescAccountClosureReasonListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescAccountClosureReasonByIdAsync")]
public async Task<Result<SystemDescAccountClosureReasonUpdateModel>> GetSystemDescAccountClosureReasonByIdAsync(
    int systemDescAccountClosureReasonId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescAccountClosureReasonByIdExtendedAsync")]
public async Task<Result<SystemDescAccountClosureReasonDTO>> GetSystemDescAccountClosureReasonByIdExtendedAsync(
    int systemDescAccountClosureReasonId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescAccountClosureReasonInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescAccountClosureReasonUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescAccountClosureReasonId)
{
    throw new NotImplementedException();
}
