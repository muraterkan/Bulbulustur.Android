using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductCategorySliderListAsync")]
public async Task<Result<List<ProductCategorySliderDTO>>> GetProductCategorySliderListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductCategorySliderByIdAsync")]
public async Task<Result<ProductCategorySliderUpdateModel>> GetProductCategorySliderByIdAsync(
    int productCategorySliderId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductCategorySliderByIdExtendedAsync")]
public async Task<Result<ProductCategorySliderDTO>> GetProductCategorySliderByIdExtendedAsync(
    int productCategorySliderId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductCategorySliderInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductCategorySliderUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productCategorySliderId)
{
    throw new NotImplementedException();
}
