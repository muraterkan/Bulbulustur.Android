using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductCategoryGuideRelatedCategoryListAsync")]
public async Task<Result<List<ProductCategoryGuideRelatedCategoryDTO>>> GetProductCategoryGuideRelatedCategoryListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductCategoryGuideRelatedCategoryByIdAsync")]
public async Task<Result<ProductCategoryGuideRelatedCategoryUpdateModel>> GetProductCategoryGuideRelatedCategoryByIdAsync(
    int productCategoryGuideRelatedCategoryId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductCategoryGuideRelatedCategoryByIdExtendedAsync")]
public async Task<Result<ProductCategoryGuideRelatedCategoryDTO>> GetProductCategoryGuideRelatedCategoryByIdExtendedAsync(
    int productCategoryGuideRelatedCategoryId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductCategoryGuideRelatedCategoryInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductCategoryGuideRelatedCategoryUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productCategoryGuideRelatedCategoryId)
{
    throw new NotImplementedException();
}
