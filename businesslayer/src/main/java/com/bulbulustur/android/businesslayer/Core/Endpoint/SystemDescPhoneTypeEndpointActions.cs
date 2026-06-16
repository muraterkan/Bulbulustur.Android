using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescPhoneTypeListAsync")]
public async Task<Result<List<SystemDescPhoneTypeDTO>>> GetSystemDescPhoneTypeListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescPhoneTypeByIdAsync")]
public async Task<Result<SystemDescPhoneTypeUpdateModel>> GetSystemDescPhoneTypeByIdAsync(
    int systemDescPhoneTypeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescPhoneTypeByIdExtendedAsync")]
public async Task<Result<SystemDescPhoneTypeDTO>> GetSystemDescPhoneTypeByIdExtendedAsync(
    int systemDescPhoneTypeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescPhoneTypeInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescPhoneTypeUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescPhoneTypeId)
{
    throw new NotImplementedException();
}
