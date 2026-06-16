using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescMaterialTypeListAsync")]
public async Task<Result<List<SystemDescMaterialTypeDTO>>> GetSystemDescMaterialTypeListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescMaterialTypeByIdAsync")]
public async Task<Result<SystemDescMaterialTypeUpdateModel>> GetSystemDescMaterialTypeByIdAsync(
    int systemDescMaterialTypeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescMaterialTypeByIdExtendedAsync")]
public async Task<Result<SystemDescMaterialTypeDTO>> GetSystemDescMaterialTypeByIdExtendedAsync(
    int systemDescMaterialTypeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescMaterialTypeInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescMaterialTypeUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescMaterialTypeId)
{
    throw new NotImplementedException();
}
