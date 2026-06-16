using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetInvoiceListAsync")]
public async Task<Result<List<InvoiceDTO>>> GetInvoiceListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetInvoiceByIdAsync")]
public async Task<Result<InvoiceUpdateModel>> GetInvoiceByIdAsync(
    int ınvoiceId)
{
    throw new NotImplementedException();
}

[HttpGet("GetInvoiceByIdExtendedAsync")]
public async Task<Result<InvoiceDTO>> GetInvoiceByIdExtendedAsync(
    int ınvoiceId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] InvoiceInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] InvoiceUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int ınvoiceId)
{
    throw new NotImplementedException();
}
