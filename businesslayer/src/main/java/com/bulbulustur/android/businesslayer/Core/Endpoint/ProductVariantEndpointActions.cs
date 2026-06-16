using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductVariantListAsync")]
public async Task<Result<List<ProductVariantDTO>>> GetProductVariantListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductVariantByIdAsync")]
public async Task<Result<ProductVariantUpdateModel>> GetProductVariantByIdAsync(
    int productVariantId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductVariantByIdExtendedAsync")]
public async Task<Result<ProductVariantDTO>> GetProductVariantByIdExtendedAsync(
    int productVariantId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductVariantInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductVariantUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productVariantId)
{
    throw new NotImplementedException();
}
