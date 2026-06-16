using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCompanyExportMarketListAsync")]
public async Task<Result<List<CompanyExportMarketDTO>>> GetCompanyExportMarketListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyExportMarketByIdAsync")]
public async Task<Result<CompanyExportMarketUpdateModel>> GetCompanyExportMarketByIdAsync(
    int companyExportMarketId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyExportMarketByIdExtendedAsync")]
public async Task<Result<CompanyExportMarketDTO>> GetCompanyExportMarketByIdExtendedAsync(
    int companyExportMarketId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CompanyExportMarketInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CompanyExportMarketUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int companyExportMarketId)
{
    throw new NotImplementedException();
}
