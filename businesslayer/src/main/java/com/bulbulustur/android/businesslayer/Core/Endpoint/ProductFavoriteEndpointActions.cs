using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductFavoriteListAsync")]
public async Task<Result<List<ProductFavoriteDTO>>> GetProductFavoriteListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductFavoriteByIdAsync")]
public async Task<Result<ProductFavoriteUpdateModel>> GetProductFavoriteByIdAsync(
    int productFavoriteId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductFavoriteByIdExtendedAsync")]
public async Task<Result<ProductFavoriteDTO>> GetProductFavoriteByIdExtendedAsync(
    int productFavoriteId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductFavoriteInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductFavoriteUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productFavoriteId)
{
    throw new NotImplementedException();
}
