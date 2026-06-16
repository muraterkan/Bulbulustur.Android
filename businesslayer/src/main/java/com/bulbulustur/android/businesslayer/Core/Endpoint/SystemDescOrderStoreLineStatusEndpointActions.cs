using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescOrderStoreLineStatusListAsync")]
public async Task<Result<List<SystemDescOrderStoreLineStatusDTO>>> GetSystemDescOrderStoreLineStatusListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescOrderStoreLineStatusByIdAsync")]
public async Task<Result<SystemDescOrderStoreLineStatusUpdateModel>> GetSystemDescOrderStoreLineStatusByIdAsync(
    int systemDescOrderStoreLineStatusId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescOrderStoreLineStatusByIdExtendedAsync")]
public async Task<Result<SystemDescOrderStoreLineStatusDTO>> GetSystemDescOrderStoreLineStatusByIdExtendedAsync(
    int systemDescOrderStoreLineStatusId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescOrderStoreLineStatusInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescOrderStoreLineStatusUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescOrderStoreLineStatusId)
{
    throw new NotImplementedException();
}
