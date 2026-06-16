using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductSizeTypeByCategoryListAsync")]
public async Task<Result<List<ProductSizeTypeByCategoryDTO>>> GetProductSizeTypeByCategoryListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductSizeTypeByCategoryByIdAsync")]
public async Task<Result<ProductSizeTypeByCategoryUpdateModel>> GetProductSizeTypeByCategoryByIdAsync(
    int productSizeTypeByCategoryId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductSizeTypeByCategoryByIdExtendedAsync")]
public async Task<Result<ProductSizeTypeByCategoryDTO>> GetProductSizeTypeByCategoryByIdExtendedAsync(
    int productSizeTypeByCategoryId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductSizeTypeByCategoryInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductSizeTypeByCategoryUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productSizeTypeByCategoryId)
{
    throw new NotImplementedException();
}
