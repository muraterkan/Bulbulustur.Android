using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductClickListAsync")]
public async Task<Result<List<ProductClickDTO>>> GetProductClickListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductClickByIdAsync")]
public async Task<Result<ProductClickUpdateModel>> GetProductClickByIdAsync(
    int productClickId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductClickByIdExtendedAsync")]
public async Task<Result<ProductClickDTO>> GetProductClickByIdExtendedAsync(
    int productClickId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductClickInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductClickUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productClickId)
{
    throw new NotImplementedException();
}
