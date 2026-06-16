using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCompanyCapabilityListAsync")]
public async Task<Result<List<CompanyCapabilityDTO>>> GetCompanyCapabilityListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyCapabilityByIdAsync")]
public async Task<Result<CompanyCapabilityUpdateModel>> GetCompanyCapabilityByIdAsync(
    int companyCapabilityId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyCapabilityByIdExtendedAsync")]
public async Task<Result<CompanyCapabilityDTO>> GetCompanyCapabilityByIdExtendedAsync(
    int companyCapabilityId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CompanyCapabilityInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CompanyCapabilityUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int companyCapabilityId)
{
    throw new NotImplementedException();
}
