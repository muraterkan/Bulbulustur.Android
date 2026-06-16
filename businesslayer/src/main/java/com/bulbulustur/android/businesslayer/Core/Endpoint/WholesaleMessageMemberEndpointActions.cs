using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleMessageMemberListAsync")]
public async Task<Result<List<WholesaleMessageMemberDTO>>> GetWholesaleMessageMemberListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleMessageMemberByIdAsync")]
public async Task<Result<WholesaleMessageMemberUpdateModel>> GetWholesaleMessageMemberByIdAsync(
    int wholesaleMessageMemberId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleMessageMemberByIdExtendedAsync")]
public async Task<Result<WholesaleMessageMemberDTO>> GetWholesaleMessageMemberByIdExtendedAsync(
    int wholesaleMessageMemberId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleMessageMemberInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleMessageMemberUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleMessageMemberId)
{
    throw new NotImplementedException();
}
