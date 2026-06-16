using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductCategoryPopularListAsync")]
public async Task<Result<List<ProductCategoryPopularDTO>>> GetProductCategoryPopularListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductCategoryPopularByIdAsync")]
public async Task<Result<ProductCategoryPopularUpdateModel>> GetProductCategoryPopularByIdAsync(
    int productCategoryPopularId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductCategoryPopularByIdExtendedAsync")]
public async Task<Result<ProductCategoryPopularDTO>> GetProductCategoryPopularByIdExtendedAsync(
    int productCategoryPopularId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductCategoryPopularInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductCategoryPopularUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productCategoryPopularId)
{
    throw new NotImplementedException();
}
