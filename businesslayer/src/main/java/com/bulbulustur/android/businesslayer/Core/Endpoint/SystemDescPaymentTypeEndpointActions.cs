using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescPaymentTypeListAsync")]
public async Task<Result<List<SystemDescPaymentTypeDTO>>> GetSystemDescPaymentTypeListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescPaymentTypeByIdAsync")]
public async Task<Result<SystemDescPaymentTypeUpdateModel>> GetSystemDescPaymentTypeByIdAsync(
    int systemDescPaymentTypeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescPaymentTypeByIdExtendedAsync")]
public async Task<Result<SystemDescPaymentTypeDTO>> GetSystemDescPaymentTypeByIdExtendedAsync(
    int systemDescPaymentTypeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescPaymentTypeInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescPaymentTypeUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescPaymentTypeId)
{
    throw new NotImplementedException();
}
