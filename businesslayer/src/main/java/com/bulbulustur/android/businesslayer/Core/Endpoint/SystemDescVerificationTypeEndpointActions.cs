using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescVerificationTypeListAsync")]
public async Task<Result<List<SystemDescVerificationTypeDTO>>> GetSystemDescVerificationTypeListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescVerificationTypeByIdAsync")]
public async Task<Result<SystemDescVerificationTypeUpdateModel>> GetSystemDescVerificationTypeByIdAsync(
    int systemDescVerificationTypeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescVerificationTypeByIdExtendedAsync")]
public async Task<Result<SystemDescVerificationTypeDTO>> GetSystemDescVerificationTypeByIdExtendedAsync(
    int systemDescVerificationTypeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescVerificationTypeInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescVerificationTypeUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescVerificationTypeId)
{
    throw new NotImplementedException();
}
