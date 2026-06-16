using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescCargoCompanyBranchListAsync")]
public async Task<Result<List<SystemDescCargoCompanyBranchDTO>>> GetSystemDescCargoCompanyBranchListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescCargoCompanyBranchByIdAsync")]
public async Task<Result<SystemDescCargoCompanyBranchUpdateModel>> GetSystemDescCargoCompanyBranchByIdAsync(
    int systemDescCargoCompanyBranchId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescCargoCompanyBranchByIdExtendedAsync")]
public async Task<Result<SystemDescCargoCompanyBranchDTO>> GetSystemDescCargoCompanyBranchByIdExtendedAsync(
    int systemDescCargoCompanyBranchId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescCargoCompanyBranchInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescCargoCompanyBranchUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescCargoCompanyBranchId)
{
    throw new NotImplementedException();
}
