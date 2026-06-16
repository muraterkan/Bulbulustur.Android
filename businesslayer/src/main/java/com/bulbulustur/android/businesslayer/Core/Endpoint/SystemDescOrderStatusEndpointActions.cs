using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescOrderStatusListAsync")]
public async Task<Result<List<SystemDescOrderStatusDTO>>> GetSystemDescOrderStatusListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescOrderStatusByIdAsync")]
public async Task<Result<SystemDescOrderStatusUpdateModel>> GetSystemDescOrderStatusByIdAsync(
    int systemDescOrderStatusId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescOrderStatusByIdExtendedAsync")]
public async Task<Result<SystemDescOrderStatusDTO>> GetSystemDescOrderStatusByIdExtendedAsync(
    int systemDescOrderStatusId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescOrderStatusInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescOrderStatusUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescOrderStatusId)
{
    throw new NotImplementedException();
}
