using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductPropertyCategoryListAsync")]
public async Task<Result<List<ProductPropertyCategoryDTO>>> GetProductPropertyCategoryListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductPropertyCategoryByIdAsync")]
public async Task<Result<ProductPropertyCategoryUpdateModel>> GetProductPropertyCategoryByIdAsync(
    int productPropertyCategoryId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductPropertyCategoryByIdExtendedAsync")]
public async Task<Result<ProductPropertyCategoryDTO>> GetProductPropertyCategoryByIdExtendedAsync(
    int productPropertyCategoryId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductPropertyCategoryInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductPropertyCategoryUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productPropertyCategoryId)
{
    throw new NotImplementedException();
}
