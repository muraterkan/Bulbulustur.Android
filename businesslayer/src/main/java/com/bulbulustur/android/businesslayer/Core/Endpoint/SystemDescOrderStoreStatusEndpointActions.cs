using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescOrderStoreStatusListAsync")]
public async Task<Result<List<SystemDescOrderStoreStatusDTO>>> GetSystemDescOrderStoreStatusListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescOrderStoreStatusByIdAsync")]
public async Task<Result<SystemDescOrderStoreStatusUpdateModel>> GetSystemDescOrderStoreStatusByIdAsync(
    int systemDescOrderStoreStatusId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescOrderStoreStatusByIdExtendedAsync")]
public async Task<Result<SystemDescOrderStoreStatusDTO>> GetSystemDescOrderStoreStatusByIdExtendedAsync(
    int systemDescOrderStoreStatusId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescOrderStoreStatusInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescOrderStoreStatusUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescOrderStoreStatusId)
{
    throw new NotImplementedException();
}
