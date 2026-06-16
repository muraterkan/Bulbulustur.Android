using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetMemberBankAccountListAsync")]
public async Task<Result<List<MemberBankAccountDTO>>> GetMemberBankAccountListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberBankAccountByIdAsync")]
public async Task<Result<MemberBankAccountUpdateModel>> GetMemberBankAccountByIdAsync(
    int memberBankAccountId)
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberBankAccountByIdExtendedAsync")]
public async Task<Result<MemberBankAccountDTO>> GetMemberBankAccountByIdExtendedAsync(
    int memberBankAccountId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] MemberBankAccountInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] MemberBankAccountUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int memberBankAccountId)
{
    throw new NotImplementedException();
}
