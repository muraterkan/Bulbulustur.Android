using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductAboutListAsync")]
public async Task<Result<List<ProductAboutDTO>>> GetProductAboutListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductAboutByIdAsync")]
public async Task<Result<ProductAboutUpdateModel>> GetProductAboutByIdAsync(
    int productAboutId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductAboutByIdExtendedAsync")]
public async Task<Result<ProductAboutDTO>> GetProductAboutByIdExtendedAsync(
    int productAboutId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductAboutInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductAboutUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productAboutId)
{
    throw new NotImplementedException();
}
