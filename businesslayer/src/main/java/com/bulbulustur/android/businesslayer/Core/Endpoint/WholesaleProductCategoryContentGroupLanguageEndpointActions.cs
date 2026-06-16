using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleProductCategoryContentGroupLanguageListAsync")]
public async Task<Result<List<WholesaleProductCategoryContentGroupLanguageDTO>>> GetWholesaleProductCategoryContentGroupLanguageListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductCategoryContentGroupLanguageByIdAsync")]
public async Task<Result<WholesaleProductCategoryContentGroupLanguageUpdateModel>> GetWholesaleProductCategoryContentGroupLanguageByIdAsync(
    int wholesaleProductCategoryContentGroupLanguageId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductCategoryContentGroupLanguageByIdExtendedAsync")]
public async Task<Result<WholesaleProductCategoryContentGroupLanguageDTO>> GetWholesaleProductCategoryContentGroupLanguageByIdExtendedAsync(
    int wholesaleProductCategoryContentGroupLanguageId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleProductCategoryContentGroupLanguageInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleProductCategoryContentGroupLanguageUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleProductCategoryContentGroupLanguageId)
{
    throw new NotImplementedException();
}
