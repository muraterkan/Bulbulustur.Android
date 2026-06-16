using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCompanyVerificationListAsync")]
public async Task<Result<List<CompanyVerificationDTO>>> GetCompanyVerificationListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyVerificationByIdAsync")]
public async Task<Result<CompanyVerificationUpdateModel>> GetCompanyVerificationByIdAsync(
    int companyVerificationId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyVerificationByIdExtendedAsync")]
public async Task<Result<CompanyVerificationDTO>> GetCompanyVerificationByIdExtendedAsync(
    int companyVerificationId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CompanyVerificationInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CompanyVerificationUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int companyVerificationId)
{
    throw new NotImplementedException();
}
