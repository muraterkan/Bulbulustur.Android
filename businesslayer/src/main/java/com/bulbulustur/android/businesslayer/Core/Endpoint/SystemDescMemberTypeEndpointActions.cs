using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescMemberTypeListAsync")]
public async Task<Result<List<SystemDescMemberTypeDTO>>> GetSystemDescMemberTypeListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescMemberTypeByIdAsync")]
public async Task<Result<SystemDescMemberTypeUpdateModel>> GetSystemDescMemberTypeByIdAsync(
    int systemDescMemberTypeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescMemberTypeByIdExtendedAsync")]
public async Task<Result<SystemDescMemberTypeDTO>> GetSystemDescMemberTypeByIdExtendedAsync(
    int systemDescMemberTypeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescMemberTypeInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescMemberTypeUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescMemberTypeId)
{
    throw new NotImplementedException();
}
