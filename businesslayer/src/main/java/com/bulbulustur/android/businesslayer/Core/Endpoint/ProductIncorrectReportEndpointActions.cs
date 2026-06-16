using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductIncorrectReportListAsync")]
public async Task<Result<List<ProductIncorrectReportDTO>>> GetProductIncorrectReportListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductIncorrectReportByIdAsync")]
public async Task<Result<ProductIncorrectReportUpdateModel>> GetProductIncorrectReportByIdAsync(
    int productIncorrectReportId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductIncorrectReportByIdExtendedAsync")]
public async Task<Result<ProductIncorrectReportDTO>> GetProductIncorrectReportByIdExtendedAsync(
    int productIncorrectReportId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductIncorrectReportInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductIncorrectReportUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productIncorrectReportId)
{
    throw new NotImplementedException();
}
