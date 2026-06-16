using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescMaritalStatusListAsync")]
public async Task<Result<List<SystemDescMaritalStatusDTO>>> GetSystemDescMaritalStatusListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescMaritalStatusByIdAsync")]
public async Task<Result<SystemDescMaritalStatusUpdateModel>> GetSystemDescMaritalStatusByIdAsync(
    int systemDescMaritalStatusId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescMaritalStatusByIdExtendedAsync")]
public async Task<Result<SystemDescMaritalStatusDTO>> GetSystemDescMaritalStatusByIdExtendedAsync(
    int systemDescMaritalStatusId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescMaritalStatusInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescMaritalStatusUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescMaritalStatusId)
{
    throw new NotImplementedException();
}
