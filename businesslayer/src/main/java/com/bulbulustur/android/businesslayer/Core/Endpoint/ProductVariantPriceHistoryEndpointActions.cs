using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductVariantPriceHistoryListAsync")]
public async Task<Result<List<ProductVariantPriceHistoryDTO>>> GetProductVariantPriceHistoryListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductVariantPriceHistoryByIdAsync")]
public async Task<Result<ProductVariantPriceHistoryUpdateModel>> GetProductVariantPriceHistoryByIdAsync(
    int productVariantPriceHistoryId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductVariantPriceHistoryByIdExtendedAsync")]
public async Task<Result<ProductVariantPriceHistoryDTO>> GetProductVariantPriceHistoryByIdExtendedAsync(
    int productVariantPriceHistoryId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductVariantPriceHistoryInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductVariantPriceHistoryUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productVariantPriceHistoryId)
{
    throw new NotImplementedException();
}
