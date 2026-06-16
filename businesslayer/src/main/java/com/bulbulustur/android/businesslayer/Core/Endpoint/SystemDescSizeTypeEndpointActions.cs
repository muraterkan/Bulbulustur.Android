using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescSizeTypeListAsync")]
public async Task<Result<List<SystemDescSizeTypeDTO>>> GetSystemDescSizeTypeListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescSizeTypeByIdAsync")]
public async Task<Result<SystemDescSizeTypeUpdateModel>> GetSystemDescSizeTypeByIdAsync(
    int systemDescSizeTypeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescSizeTypeByIdExtendedAsync")]
public async Task<Result<SystemDescSizeTypeDTO>> GetSystemDescSizeTypeByIdExtendedAsync(
    int systemDescSizeTypeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescSizeTypeInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescSizeTypeUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescSizeTypeId)
{
    throw new NotImplementedException();
}
