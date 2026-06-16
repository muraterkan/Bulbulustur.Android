using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCompanyPaymentTermListAsync")]
public async Task<Result<List<CompanyPaymentTermDTO>>> GetCompanyPaymentTermListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyPaymentTermByIdAsync")]
public async Task<Result<CompanyPaymentTermUpdateModel>> GetCompanyPaymentTermByIdAsync(
    int companyPaymentTermId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyPaymentTermByIdExtendedAsync")]
public async Task<Result<CompanyPaymentTermDTO>> GetCompanyPaymentTermByIdExtendedAsync(
    int companyPaymentTermId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CompanyPaymentTermInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CompanyPaymentTermUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int companyPaymentTermId)
{
    throw new NotImplementedException();
}
