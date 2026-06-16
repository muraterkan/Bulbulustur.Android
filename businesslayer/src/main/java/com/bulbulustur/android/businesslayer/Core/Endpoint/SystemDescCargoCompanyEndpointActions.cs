using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescCargoCompanyListAsync")]
public async Task<Result<List<SystemDescCargoCompanyDTO>>> GetSystemDescCargoCompanyListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescCargoCompanyByIdAsync")]
public async Task<Result<SystemDescCargoCompanyUpdateModel>> GetSystemDescCargoCompanyByIdAsync(
    int systemDescCargoCompanyId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescCargoCompanyByIdExtendedAsync")]
public async Task<Result<SystemDescCargoCompanyDTO>> GetSystemDescCargoCompanyByIdExtendedAsync(
    int systemDescCargoCompanyId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescCargoCompanyInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescCargoCompanyUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescCargoCompanyId)
{
    throw new NotImplementedException();
}
