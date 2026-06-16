using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetMemberCouponListAsync")]
public async Task<Result<List<MemberCouponDTO>>> GetMemberCouponListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberCouponByIdAsync")]
public async Task<Result<MemberCouponUpdateModel>> GetMemberCouponByIdAsync(
    int memberCouponId)
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberCouponByIdExtendedAsync")]
public async Task<Result<MemberCouponDTO>> GetMemberCouponByIdExtendedAsync(
    int memberCouponId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] MemberCouponInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] MemberCouponUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int memberCouponId)
{
    throw new NotImplementedException();
}
