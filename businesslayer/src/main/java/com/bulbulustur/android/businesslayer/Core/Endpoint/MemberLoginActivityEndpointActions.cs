using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetMemberLoginActivityListAsync")]
public async Task<Result<List<MemberLoginActivityDTO>>> GetMemberLoginActivityListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberLoginActivityByIdAsync")]
public async Task<Result<MemberLoginActivityUpdateModel>> GetMemberLoginActivityByIdAsync(
    int memberLoginActivityId)
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberLoginActivityByIdExtendedAsync")]
public async Task<Result<MemberLoginActivityDTO>> GetMemberLoginActivityByIdExtendedAsync(
    int memberLoginActivityId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] MemberLoginActivityInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] MemberLoginActivityUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int memberLoginActivityId)
{
    throw new NotImplementedException();
}
