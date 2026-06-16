using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescVatRateListAsync")]
public async Task<Result<List<SystemDescVatRateDTO>>> GetSystemDescVatRateListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescVatRateByIdAsync")]
public async Task<Result<SystemDescVatRateUpdateModel>> GetSystemDescVatRateByIdAsync(
    int systemDescVatRateId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescVatRateByIdExtendedAsync")]
public async Task<Result<SystemDescVatRateDTO>> GetSystemDescVatRateByIdExtendedAsync(
    int systemDescVatRateId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescVatRateInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescVatRateUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescVatRateId)
{
    throw new NotImplementedException();
}
