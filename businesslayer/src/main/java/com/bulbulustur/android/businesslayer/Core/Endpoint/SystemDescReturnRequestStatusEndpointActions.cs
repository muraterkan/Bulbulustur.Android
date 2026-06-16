using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescReturnRequestStatusListAsync")]
public async Task<Result<List<SystemDescReturnRequestStatusDTO>>> GetSystemDescReturnRequestStatusListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescReturnRequestStatusByIdAsync")]
public async Task<Result<SystemDescReturnRequestStatusUpdateModel>> GetSystemDescReturnRequestStatusByIdAsync(
    int systemDescReturnRequestStatusId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescReturnRequestStatusByIdExtendedAsync")]
public async Task<Result<SystemDescReturnRequestStatusDTO>> GetSystemDescReturnRequestStatusByIdExtendedAsync(
    int systemDescReturnRequestStatusId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescReturnRequestStatusInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescReturnRequestStatusUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescReturnRequestStatusId)
{
    throw new NotImplementedException();
}
