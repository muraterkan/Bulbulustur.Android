using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescReturnRequestReasonListAsync")]
public async Task<Result<List<SystemDescReturnRequestReasonDTO>>> GetSystemDescReturnRequestReasonListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescReturnRequestReasonByIdAsync")]
public async Task<Result<SystemDescReturnRequestReasonUpdateModel>> GetSystemDescReturnRequestReasonByIdAsync(
    int systemDescReturnRequestReasonId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescReturnRequestReasonByIdExtendedAsync")]
public async Task<Result<SystemDescReturnRequestReasonDTO>> GetSystemDescReturnRequestReasonByIdExtendedAsync(
    int systemDescReturnRequestReasonId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescReturnRequestReasonInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescReturnRequestReasonUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescReturnRequestReasonId)
{
    throw new NotImplementedException();
}
