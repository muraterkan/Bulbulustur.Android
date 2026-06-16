using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductBrandGroupListAsync")]
public async Task<Result<List<ProductBrandGroupDTO>>> GetProductBrandGroupListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductBrandGroupByIdAsync")]
public async Task<Result<ProductBrandGroupUpdateModel>> GetProductBrandGroupByIdAsync(
    int productBrandGroupId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductBrandGroupByIdExtendedAsync")]
public async Task<Result<ProductBrandGroupDTO>> GetProductBrandGroupByIdExtendedAsync(
    int productBrandGroupId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductBrandGroupInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductBrandGroupUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productBrandGroupId)
{
    throw new NotImplementedException();
}
