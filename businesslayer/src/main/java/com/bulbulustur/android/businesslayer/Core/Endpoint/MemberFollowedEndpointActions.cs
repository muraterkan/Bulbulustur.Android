using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetMemberFollowedListAsync")]
public async Task<Result<List<MemberFollowedDTO>>> GetMemberFollowedListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberFollowedByIdAsync")]
public async Task<Result<MemberFollowedUpdateModel>> GetMemberFollowedByIdAsync(
    int memberFollowedId)
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberFollowedByIdExtendedAsync")]
public async Task<Result<MemberFollowedDTO>> GetMemberFollowedByIdExtendedAsync(
    int memberFollowedId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] MemberFollowedInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] MemberFollowedUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int memberFollowedId)
{
    throw new NotImplementedException();
}
