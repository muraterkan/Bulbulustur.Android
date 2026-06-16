using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCompanyAddressListAsync")]
public async Task<Result<List<CompanyAddressDTO>>> GetCompanyAddressListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyAddressByIdAsync")]
public async Task<Result<CompanyAddressUpdateModel>> GetCompanyAddressByIdAsync(
    int companyAddressId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyAddressByIdExtendedAsync")]
public async Task<Result<CompanyAddressDTO>> GetCompanyAddressByIdExtendedAsync(
    int companyAddressId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CompanyAddressInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CompanyAddressUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int companyAddressId)
{
    throw new NotImplementedException();
}
