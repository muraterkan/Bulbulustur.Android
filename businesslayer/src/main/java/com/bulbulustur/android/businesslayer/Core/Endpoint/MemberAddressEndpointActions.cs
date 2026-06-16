using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetMemberAddressListAsync")]
public async Task<Result<List<MemberAddressDTO>>> GetMemberAddressListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberAddressByIdAsync")]
public async Task<Result<MemberAddressUpdateModel>> GetMemberAddressByIdAsync(
    int memberAddressId)
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberAddressByIdExtendedAsync")]
public async Task<Result<MemberAddressDTO>> GetMemberAddressByIdExtendedAsync(
    int memberAddressId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] MemberAddressInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] MemberAddressUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int memberAddressId)
{
    throw new NotImplementedException();
}
