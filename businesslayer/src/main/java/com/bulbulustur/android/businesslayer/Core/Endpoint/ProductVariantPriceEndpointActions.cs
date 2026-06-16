using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductVariantPriceListAsync")]
public async Task<Result<List<ProductVariantPriceDTO>>> GetProductVariantPriceListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductVariantPriceByIdAsync")]
public async Task<Result<ProductVariantPriceUpdateModel>> GetProductVariantPriceByIdAsync(
    int productVariantPriceId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductVariantPriceByIdExtendedAsync")]
public async Task<Result<ProductVariantPriceDTO>> GetProductVariantPriceByIdExtendedAsync(
    int productVariantPriceId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductVariantPriceInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductVariantPriceUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productVariantPriceId)
{
    throw new NotImplementedException();
}
