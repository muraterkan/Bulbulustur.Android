using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCompanyCertificateListAsync")]
public async Task<Result<List<CompanyCertificateDTO>>> GetCompanyCertificateListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyCertificateByIdAsync")]
public async Task<Result<CompanyCertificateUpdateModel>> GetCompanyCertificateByIdAsync(
    int companyCertificateId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyCertificateByIdExtendedAsync")]
public async Task<Result<CompanyCertificateDTO>> GetCompanyCertificateByIdExtendedAsync(
    int companyCertificateId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CompanyCertificateInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CompanyCertificateUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int companyCertificateId)
{
    throw new NotImplementedException();
}
