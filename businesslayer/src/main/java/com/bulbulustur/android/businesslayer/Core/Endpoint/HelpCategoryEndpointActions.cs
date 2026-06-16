using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetHelpCategoryListAsync")]
public async Task<Result<List<HelpCategoryDTO>>> GetHelpCategoryListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpCategoryByIdAsync")]
public async Task<Result<HelpCategoryUpdateModel>> GetHelpCategoryByIdAsync(
    int helpCategoryId)
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpCategoryByIdExtendedAsync")]
public async Task<Result<HelpCategoryDTO>> GetHelpCategoryByIdExtendedAsync(
    int helpCategoryId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] HelpCategoryInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] HelpCategoryUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int helpCategoryId)
{
    throw new NotImplementedException();
}
