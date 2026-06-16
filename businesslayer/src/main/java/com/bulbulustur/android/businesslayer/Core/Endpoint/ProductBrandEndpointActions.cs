using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductBrandListAsync")]
public async Task<Result<List<ProductBrandDTO>>> GetProductBrandListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductBrandByIdAsync")]
public async Task<Result<ProductBrandUpdateModel>> GetProductBrandByIdAsync(
    int productBrandId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductBrandByIdExtendedAsync")]
public async Task<Result<ProductBrandDTO>> GetProductBrandByIdExtendedAsync(
    int productBrandId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductBrandInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductBrandUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productBrandId)
{
    throw new NotImplementedException();
}
