using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCompanyBankAccountListAsync")]
public async Task<Result<List<CompanyBankAccountDTO>>> GetCompanyBankAccountListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyBankAccountByIdAsync")]
public async Task<Result<CompanyBankAccountUpdateModel>> GetCompanyBankAccountByIdAsync(
    int companyBankAccountId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyBankAccountByIdExtendedAsync")]
public async Task<Result<CompanyBankAccountDTO>> GetCompanyBankAccountByIdExtendedAsync(
    int companyBankAccountId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CompanyBankAccountInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CompanyBankAccountUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int companyBankAccountId)
{
    throw new NotImplementedException();
}
