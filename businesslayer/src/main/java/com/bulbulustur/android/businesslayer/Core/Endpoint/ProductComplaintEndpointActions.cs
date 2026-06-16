using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductComplaintListAsync")]
public async Task<Result<List<ProductComplaintDTO>>> GetProductComplaintListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductComplaintByIdAsync")]
public async Task<Result<ProductComplaintUpdateModel>> GetProductComplaintByIdAsync(
    int productComplaintId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductComplaintByIdExtendedAsync")]
public async Task<Result<ProductComplaintDTO>> GetProductComplaintByIdExtendedAsync(
    int productComplaintId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductComplaintInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductComplaintUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productComplaintId)
{
    throw new NotImplementedException();
}
