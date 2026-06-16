using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetMemberActivityListAsync")]
public async Task<Result<List<MemberActivityDTO>>> GetMemberActivityListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberActivityByIdAsync")]
public async Task<Result<MemberActivityUpdateModel>> GetMemberActivityByIdAsync(
    int memberActivityId)
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberActivityByIdExtendedAsync")]
public async Task<Result<MemberActivityDTO>> GetMemberActivityByIdExtendedAsync(
    int memberActivityId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] MemberActivityInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] MemberActivityUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int memberActivityId)
{
    throw new NotImplementedException();
}
