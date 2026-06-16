using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSupportConditionListAsync")]
public async Task<Result<List<SupportConditionDTO>>> GetSupportConditionListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSupportConditionByIdAsync")]
public async Task<Result<SupportConditionUpdateModel>> GetSupportConditionByIdAsync(
    int supportConditionId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSupportConditionByIdExtendedAsync")]
public async Task<Result<SupportConditionDTO>> GetSupportConditionByIdExtendedAsync(
    int supportConditionId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SupportConditionInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SupportConditionUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int supportConditionId)
{
    throw new NotImplementedException();
}
