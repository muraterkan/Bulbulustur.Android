using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductLowPriceReportListAsync")]
public async Task<Result<List<ProductLowPriceReportDTO>>> GetProductLowPriceReportListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductLowPriceReportByIdAsync")]
public async Task<Result<ProductLowPriceReportUpdateModel>> GetProductLowPriceReportByIdAsync(
    int productLowPriceReportId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductLowPriceReportByIdExtendedAsync")]
public async Task<Result<ProductLowPriceReportDTO>> GetProductLowPriceReportByIdExtendedAsync(
    int productLowPriceReportId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductLowPriceReportInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductLowPriceReportUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productLowPriceReportId)
{
    throw new NotImplementedException();
}
