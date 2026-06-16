using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCompanyBusinessTypeListAsync")]
public async Task<Result<List<CompanyBusinessTypeDTO>>> GetCompanyBusinessTypeListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyBusinessTypeByIdAsync")]
public async Task<Result<CompanyBusinessTypeUpdateModel>> GetCompanyBusinessTypeByIdAsync(
    int companyBusinessTypeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyBusinessTypeByIdExtendedAsync")]
public async Task<Result<CompanyBusinessTypeDTO>> GetCompanyBusinessTypeByIdExtendedAsync(
    int companyBusinessTypeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CompanyBusinessTypeInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CompanyBusinessTypeUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int companyBusinessTypeId)
{
    throw new NotImplementedException();
}
