using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescCargoDesiListAsync")]
public async Task<Result<List<SystemDescCargoDesiDTO>>> GetSystemDescCargoDesiListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescCargoDesiByIdAsync")]
public async Task<Result<SystemDescCargoDesiUpdateModel>> GetSystemDescCargoDesiByIdAsync(
    int systemDescCargoDesiId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescCargoDesiByIdExtendedAsync")]
public async Task<Result<SystemDescCargoDesiDTO>> GetSystemDescCargoDesiByIdExtendedAsync(
    int systemDescCargoDesiId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescCargoDesiInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescCargoDesiUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescCargoDesiId)
{
    throw new NotImplementedException();
}
