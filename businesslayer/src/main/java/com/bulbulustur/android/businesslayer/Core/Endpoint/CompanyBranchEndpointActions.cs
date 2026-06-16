using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCompanyBranchListAsync")]
public async Task<Result<List<CompanyBranchDTO>>> GetCompanyBranchListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyBranchByIdAsync")]
public async Task<Result<CompanyBranchUpdateModel>> GetCompanyBranchByIdAsync(
    int companyBranchId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyBranchByIdExtendedAsync")]
public async Task<Result<CompanyBranchDTO>> GetCompanyBranchByIdExtendedAsync(
    int companyBranchId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CompanyBranchInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CompanyBranchUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int companyBranchId)
{
    throw new NotImplementedException();
}
