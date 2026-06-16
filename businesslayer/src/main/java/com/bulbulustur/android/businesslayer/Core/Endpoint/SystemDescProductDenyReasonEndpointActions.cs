using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescProductDenyReasonListAsync")]
public async Task<Result<List<SystemDescProductDenyReasonDTO>>> GetSystemDescProductDenyReasonListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescProductDenyReasonByIdAsync")]
public async Task<Result<SystemDescProductDenyReasonUpdateModel>> GetSystemDescProductDenyReasonByIdAsync(
    int systemDescProductDenyReasonId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescProductDenyReasonByIdExtendedAsync")]
public async Task<Result<SystemDescProductDenyReasonDTO>> GetSystemDescProductDenyReasonByIdExtendedAsync(
    int systemDescProductDenyReasonId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescProductDenyReasonInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescProductDenyReasonUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescProductDenyReasonId)
{
    throw new NotImplementedException();
}
