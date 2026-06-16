using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleProductCategoryContentGroupListAsync")]
public async Task<Result<List<WholesaleProductCategoryContentGroupDTO>>> GetWholesaleProductCategoryContentGroupListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductCategoryContentGroupByIdAsync")]
public async Task<Result<WholesaleProductCategoryContentGroupUpdateModel>> GetWholesaleProductCategoryContentGroupByIdAsync(
    int wholesaleProductCategoryContentGroupId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductCategoryContentGroupByIdExtendedAsync")]
public async Task<Result<WholesaleProductCategoryContentGroupDTO>> GetWholesaleProductCategoryContentGroupByIdExtendedAsync(
    int wholesaleProductCategoryContentGroupId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleProductCategoryContentGroupInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleProductCategoryContentGroupUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleProductCategoryContentGroupId)
{
    throw new NotImplementedException();
}
