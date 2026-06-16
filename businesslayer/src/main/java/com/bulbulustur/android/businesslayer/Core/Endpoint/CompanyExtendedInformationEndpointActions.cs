using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCompanyExtendedInformationListAsync")]
public async Task<Result<List<CompanyExtendedInformationDTO>>> GetCompanyExtendedInformationListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyExtendedInformationByIdAsync")]
public async Task<Result<CompanyExtendedInformationUpdateModel>> GetCompanyExtendedInformationByIdAsync(
    int companyExtendedInformationId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyExtendedInformationByIdExtendedAsync")]
public async Task<Result<CompanyExtendedInformationDTO>> GetCompanyExtendedInformationByIdExtendedAsync(
    int companyExtendedInformationId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CompanyExtendedInformationInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CompanyExtendedInformationUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int companyExtendedInformationId)
{
    throw new NotImplementedException();
}
