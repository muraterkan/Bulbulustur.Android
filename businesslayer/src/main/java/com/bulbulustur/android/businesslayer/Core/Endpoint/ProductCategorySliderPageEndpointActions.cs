using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductCategorySliderPageListAsync")]
public async Task<Result<List<ProductCategorySliderPageDTO>>> GetProductCategorySliderPageListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductCategorySliderPageByIdAsync")]
public async Task<Result<ProductCategorySliderPageUpdateModel>> GetProductCategorySliderPageByIdAsync(
    int productCategorySliderPageId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductCategorySliderPageByIdExtendedAsync")]
public async Task<Result<ProductCategorySliderPageDTO>> GetProductCategorySliderPageByIdExtendedAsync(
    int productCategorySliderPageId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductCategorySliderPageInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductCategorySliderPageUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productCategorySliderPageId)
{
    throw new NotImplementedException();
}
