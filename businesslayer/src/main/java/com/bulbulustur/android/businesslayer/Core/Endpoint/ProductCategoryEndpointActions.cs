using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductCategoryListAsync")]
public async Task<Result<List<ProductCategoryDTO>>> GetProductCategoryListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductCategoryByIdAsync")]
public async Task<Result<ProductCategoryUpdateModel>> GetProductCategoryByIdAsync(
    int productCategoryId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductCategoryByIdExtendedAsync")]
public async Task<Result<ProductCategoryDTO>> GetProductCategoryByIdExtendedAsync(
    int productCategoryId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductCategoryInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductCategoryUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productCategoryId)
{
    throw new NotImplementedException();
}
