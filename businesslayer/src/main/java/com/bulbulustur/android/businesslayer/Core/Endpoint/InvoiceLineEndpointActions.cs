using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetInvoiceLineListAsync")]
public async Task<Result<List<InvoiceLineDTO>>> GetInvoiceLineListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetInvoiceLineByIdAsync")]
public async Task<Result<InvoiceLineUpdateModel>> GetInvoiceLineByIdAsync(
    int ınvoiceLineId)
{
    throw new NotImplementedException();
}

[HttpGet("GetInvoiceLineByIdExtendedAsync")]
public async Task<Result<InvoiceLineDTO>> GetInvoiceLineByIdExtendedAsync(
    int ınvoiceLineId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] InvoiceLineInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] InvoiceLineUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int ınvoiceLineId)
{
    throw new NotImplementedException();
}
