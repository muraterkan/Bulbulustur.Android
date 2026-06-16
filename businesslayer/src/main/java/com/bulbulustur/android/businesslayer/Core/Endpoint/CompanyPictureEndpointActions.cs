using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCompanyPictureListAsync")]
public async Task<Result<List<CompanyPictureDTO>>> GetCompanyPictureListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyPictureByIdAsync")]
public async Task<Result<CompanyPictureUpdateModel>> GetCompanyPictureByIdAsync(
    int companyPictureId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCompanyPictureByIdExtendedAsync")]
public async Task<Result<CompanyPictureDTO>> GetCompanyPictureByIdExtendedAsync(
    int companyPictureId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CompanyPictureInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CompanyPictureUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int companyPictureId)
{
    throw new NotImplementedException();
}
