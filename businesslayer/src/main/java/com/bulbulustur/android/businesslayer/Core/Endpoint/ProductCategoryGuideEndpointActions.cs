using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductCategoryGuideListAsync")]
public async Task<Result<List<ProductCategoryGuideDTO>>> GetProductCategoryGuideListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductCategoryGuideByIdAsync")]
public async Task<Result<ProductCategoryGuideUpdateModel>> GetProductCategoryGuideByIdAsync(
    int productCategoryGuideId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductCategoryGuideByIdExtendedAsync")]
public async Task<Result<ProductCategoryGuideDTO>> GetProductCategoryGuideByIdExtendedAsync(
    int productCategoryGuideId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductCategoryGuideInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductCategoryGuideUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productCategoryGuideId)
{
    throw new NotImplementedException();
}
