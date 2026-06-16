using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductListAsync")]
public async Task<Result<List<ProductDTO>>> GetProductListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductByIdAsync")]
public async Task<Result<ProductUpdateModel>> GetProductByIdAsync(
    int productId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductByIdExtendedAsync")]
public async Task<Result<ProductDTO>> GetProductByIdExtendedAsync(
    int productId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productId)
{
    throw new NotImplementedException();
}
