using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetMemberBlockListAsync")]
public async Task<Result<List<MemberBlockDTO>>> GetMemberBlockListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberBlockByIdAsync")]
public async Task<Result<MemberBlockUpdateModel>> GetMemberBlockByIdAsync(
    int memberBlockId)
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberBlockByIdExtendedAsync")]
public async Task<Result<MemberBlockDTO>> GetMemberBlockByIdExtendedAsync(
    int memberBlockId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] MemberBlockInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] MemberBlockUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int memberBlockId)
{
    throw new NotImplementedException();
}
