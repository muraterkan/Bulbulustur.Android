using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleProductCategoryContentListAsync")]
public async Task<Result<List<WholesaleProductCategoryContentDTO>>> GetWholesaleProductCategoryContentListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductCategoryContentByIdAsync")]
public async Task<Result<WholesaleProductCategoryContentUpdateModel>> GetWholesaleProductCategoryContentByIdAsync(
    int wholesaleProductCategoryContentId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductCategoryContentByIdExtendedAsync")]
public async Task<Result<WholesaleProductCategoryContentDTO>> GetWholesaleProductCategoryContentByIdExtendedAsync(
    int wholesaleProductCategoryContentId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleProductCategoryContentInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleProductCategoryContentUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleProductCategoryContentId)
{
    throw new NotImplementedException();
}
