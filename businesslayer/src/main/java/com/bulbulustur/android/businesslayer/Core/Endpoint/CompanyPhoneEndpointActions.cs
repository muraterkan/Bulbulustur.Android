using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCompanyPhoneListAsync")]
public async Task<Result<List<CompanyPhoneDTO>>> GetCompanyPhoneListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyPhoneByIdAsync")]
public async Task<Result<CompanyPhoneUpdateModel>> GetCompanyPhoneByIdAsync(
    int companyPhoneId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyPhoneByIdExtendedAsync")]
public async Task<Result<CompanyPhoneDTO>> GetCompanyPhoneByIdExtendedAsync(
    int companyPhoneId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CompanyPhoneInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CompanyPhoneUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int companyPhoneId)
{
    throw new NotImplementedException();
}
