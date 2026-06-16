using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescOrderCancelationTypeListAsync")]
public async Task<Result<List<SystemDescOrderCancelationTypeDTO>>> GetSystemDescOrderCancelationTypeListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescOrderCancelationTypeByIdAsync")]
public async Task<Result<SystemDescOrderCancelationTypeUpdateModel>> GetSystemDescOrderCancelationTypeByIdAsync(
    int systemDescOrderCancelationTypeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescOrderCancelationTypeByIdExtendedAsync")]
public async Task<Result<SystemDescOrderCancelationTypeDTO>> GetSystemDescOrderCancelationTypeByIdExtendedAsync(
    int systemDescOrderCancelationTypeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescOrderCancelationTypeInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescOrderCancelationTypeUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescOrderCancelationTypeId)
{
    throw new NotImplementedException();
}
