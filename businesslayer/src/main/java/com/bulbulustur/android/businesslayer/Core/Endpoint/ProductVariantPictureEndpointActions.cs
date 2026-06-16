using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductVariantPictureListAsync")]
public async Task<Result<List<ProductVariantPictureDTO>>> GetProductVariantPictureListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductVariantPictureByIdAsync")]
public async Task<Result<ProductVariantPictureUpdateModel>> GetProductVariantPictureByIdAsync(
    int productVariantPictureId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductVariantPictureByIdExtendedAsync")]
public async Task<Result<ProductVariantPictureDTO>> GetProductVariantPictureByIdExtendedAsync(
    int productVariantPictureId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductVariantPictureInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductVariantPictureUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productVariantPictureId)
{
    throw new NotImplementedException();
}
