using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductCategoryGuideCardListAsync")]
public async Task<Result<List<ProductCategoryGuideCardDTO>>> GetProductCategoryGuideCardListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductCategoryGuideCardByIdAsync")]
public async Task<Result<ProductCategoryGuideCardUpdateModel>> GetProductCategoryGuideCardByIdAsync(
    int productCategoryGuideCardId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductCategoryGuideCardByIdExtendedAsync")]
public async Task<Result<ProductCategoryGuideCardDTO>> GetProductCategoryGuideCardByIdExtendedAsync(
    int productCategoryGuideCardId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductCategoryGuideCardInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductCategoryGuideCardUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productCategoryGuideCardId)
{
    throw new NotImplementedException();
}
