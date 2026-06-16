using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCompanyListAsync")]
public async Task<Result<List<CompanyDTO>>> GetCompanyListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyByIdAsync")]
public async Task<Result<CompanyUpdateModel>> GetCompanyByIdAsync(
    int companyId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyByIdExtendedAsync")]
public async Task<Result<CompanyDTO>> GetCompanyByIdExtendedAsync(
    int companyId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CompanyInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CompanyUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int companyId)
{
    throw new NotImplementedException();
}
