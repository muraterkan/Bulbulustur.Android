using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescAccountActivityTypeListAsync")]
public async Task<Result<List<SystemDescAccountActivityTypeDTO>>> GetSystemDescAccountActivityTypeListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescAccountActivityTypeByIdAsync")]
public async Task<Result<SystemDescAccountActivityTypeUpdateModel>> GetSystemDescAccountActivityTypeByIdAsync(
    int systemDescAccountActivityTypeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescAccountActivityTypeByIdExtendedAsync")]
public async Task<Result<SystemDescAccountActivityTypeDTO>> GetSystemDescAccountActivityTypeByIdExtendedAsync(
    int systemDescAccountActivityTypeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescAccountActivityTypeInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescAccountActivityTypeUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescAccountActivityTypeId)
{
    throw new NotImplementedException();
}
