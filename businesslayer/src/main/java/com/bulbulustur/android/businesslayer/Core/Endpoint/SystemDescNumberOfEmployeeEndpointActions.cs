using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescNumberOfEmployeeListAsync")]
public async Task<Result<List<SystemDescNumberOfEmployeeDTO>>> GetSystemDescNumberOfEmployeeListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescNumberOfEmployeeByIdAsync")]
public async Task<Result<SystemDescNumberOfEmployeeUpdateModel>> GetSystemDescNumberOfEmployeeByIdAsync(
    int systemDescNumberOfEmployeeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescNumberOfEmployeeByIdExtendedAsync")]
public async Task<Result<SystemDescNumberOfEmployeeDTO>> GetSystemDescNumberOfEmployeeByIdExtendedAsync(
    int systemDescNumberOfEmployeeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescNumberOfEmployeeInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescNumberOfEmployeeUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescNumberOfEmployeeId)
{
    throw new NotImplementedException();
}
