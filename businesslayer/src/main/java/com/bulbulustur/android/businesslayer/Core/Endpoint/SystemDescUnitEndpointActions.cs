using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescUnitListAsync")]
public async Task<Result<List<SystemDescUnitDTO>>> GetSystemDescUnitListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescUnitByIdAsync")]
public async Task<Result<SystemDescUnitUpdateModel>> GetSystemDescUnitByIdAsync(
    int systemDescUnitId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescUnitByIdExtendedAsync")]
public async Task<Result<SystemDescUnitDTO>> GetSystemDescUnitByIdExtendedAsync(
    int systemDescUnitId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescUnitInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescUnitUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescUnitId)
{
    throw new NotImplementedException();
}
