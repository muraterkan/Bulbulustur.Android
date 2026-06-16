using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductComplementaryItemListAsync")]
public async Task<Result<List<ProductComplementaryItemDTO>>> GetProductComplementaryItemListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductComplementaryItemByIdAsync")]
public async Task<Result<ProductComplementaryItemUpdateModel>> GetProductComplementaryItemByIdAsync(
    int productComplementaryItemId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductComplementaryItemByIdExtendedAsync")]
public async Task<Result<ProductComplementaryItemDTO>> GetProductComplementaryItemByIdExtendedAsync(
    int productComplementaryItemId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductComplementaryItemInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductComplementaryItemUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productComplementaryItemId)
{
    throw new NotImplementedException();
}
