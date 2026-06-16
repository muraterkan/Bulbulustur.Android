using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductBrandGroupMapListAsync")]
public async Task<Result<List<ProductBrandGroupMapDTO>>> GetProductBrandGroupMapListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductBrandGroupMapByIdAsync")]
public async Task<Result<ProductBrandGroupMapUpdateModel>> GetProductBrandGroupMapByIdAsync(
    int productBrandGroupMapId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductBrandGroupMapByIdExtendedAsync")]
public async Task<Result<ProductBrandGroupMapDTO>> GetProductBrandGroupMapByIdExtendedAsync(
    int productBrandGroupMapId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductBrandGroupMapInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductBrandGroupMapUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productBrandGroupMapId)
{
    throw new NotImplementedException();
}
