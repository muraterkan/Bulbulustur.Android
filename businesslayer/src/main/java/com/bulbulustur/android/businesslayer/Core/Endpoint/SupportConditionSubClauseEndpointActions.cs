using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSupportConditionSubClauseListAsync")]
public async Task<Result<List<SupportConditionSubClauseDTO>>> GetSupportConditionSubClauseListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSupportConditionSubClauseByIdAsync")]
public async Task<Result<SupportConditionSubClauseUpdateModel>> GetSupportConditionSubClauseByIdAsync(
    int supportConditionSubClauseId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSupportConditionSubClauseByIdExtendedAsync")]
public async Task<Result<SupportConditionSubClauseDTO>> GetSupportConditionSubClauseByIdExtendedAsync(
    int supportConditionSubClauseId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SupportConditionSubClauseInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SupportConditionSubClauseUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int supportConditionSubClauseId)
{
    throw new NotImplementedException();
}
