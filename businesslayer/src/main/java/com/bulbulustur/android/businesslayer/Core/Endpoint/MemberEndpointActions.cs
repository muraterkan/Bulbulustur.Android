using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetMemberListAsync")]
public async Task<Result<List<MemberDTO>>> GetMemberListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberByIdAsync")]
public async Task<Result<MemberUpdateModel>> GetMemberByIdAsync(
    int memberId)
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberByIdExtendedAsync")]
public async Task<Result<MemberDTO>> GetMemberByIdExtendedAsync(
    int memberId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] MemberInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] MemberUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int memberId)
{
    throw new NotImplementedException();
}
