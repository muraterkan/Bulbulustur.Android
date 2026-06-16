using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescBusinessTypeListAsync")]
public async Task<Result<List<SystemDescBusinessTypeDTO>>> GetSystemDescBusinessTypeListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescBusinessTypeByIdAsync")]
public async Task<Result<SystemDescBusinessTypeUpdateModel>> GetSystemDescBusinessTypeByIdAsync(
    int systemDescBusinessTypeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescBusinessTypeByIdExtendedAsync")]
public async Task<Result<SystemDescBusinessTypeDTO>> GetSystemDescBusinessTypeByIdExtendedAsync(
    int systemDescBusinessTypeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescBusinessTypeInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescBusinessTypeUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescBusinessTypeId)
{
    throw new NotImplementedException();
}
