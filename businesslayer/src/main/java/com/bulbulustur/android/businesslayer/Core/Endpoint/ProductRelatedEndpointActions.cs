using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductRelatedListAsync")]
public async Task<Result<List<ProductRelatedDTO>>> GetProductRelatedListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductRelatedByIdAsync")]
public async Task<Result<ProductRelatedUpdateModel>> GetProductRelatedByIdAsync(
    int productRelatedId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductRelatedByIdExtendedAsync")]
public async Task<Result<ProductRelatedDTO>> GetProductRelatedByIdExtendedAsync(
    int productRelatedId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductRelatedInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductRelatedUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productRelatedId)
{
    throw new NotImplementedException();
}
