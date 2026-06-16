using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCompanyUserListAsync")]
public async Task<Result<List<CompanyUserDTO>>> GetCompanyUserListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyUserByIdAsync")]
public async Task<Result<CompanyUserUpdateModel>> GetCompanyUserByIdAsync(
    int companyUserId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyUserByIdExtendedAsync")]
public async Task<Result<CompanyUserDTO>> GetCompanyUserByIdExtendedAsync(
    int companyUserId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CompanyUserInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CompanyUserUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int companyUserId)
{
    throw new NotImplementedException();
}
