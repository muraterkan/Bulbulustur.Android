using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductBuyTogetherListAsync")]
public async Task<Result<List<ProductBuyTogetherDTO>>> GetProductBuyTogetherListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductBuyTogetherByIdAsync")]
public async Task<Result<ProductBuyTogetherUpdateModel>> GetProductBuyTogetherByIdAsync(
    int productBuyTogetherId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductBuyTogetherByIdExtendedAsync")]
public async Task<Result<ProductBuyTogetherDTO>> GetProductBuyTogetherByIdExtendedAsync(
    int productBuyTogetherId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductBuyTogetherInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductBuyTogetherUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productBuyTogetherId)
{
    throw new NotImplementedException();
}
