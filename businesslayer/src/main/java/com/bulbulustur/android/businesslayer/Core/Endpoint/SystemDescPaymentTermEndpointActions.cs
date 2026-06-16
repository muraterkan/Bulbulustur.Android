using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescPaymentTermListAsync")]
public async Task<Result<List<SystemDescPaymentTermDTO>>> GetSystemDescPaymentTermListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescPaymentTermByIdAsync")]
public async Task<Result<SystemDescPaymentTermUpdateModel>> GetSystemDescPaymentTermByIdAsync(
    int systemDescPaymentTermId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescPaymentTermByIdExtendedAsync")]
public async Task<Result<SystemDescPaymentTermDTO>> GetSystemDescPaymentTermByIdExtendedAsync(
    int systemDescPaymentTermId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescPaymentTermInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescPaymentTermUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescPaymentTermId)
{
    throw new NotImplementedException();
}
