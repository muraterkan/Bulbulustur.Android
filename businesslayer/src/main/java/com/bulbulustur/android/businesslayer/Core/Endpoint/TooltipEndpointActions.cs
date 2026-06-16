using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetTooltipListAsync")]
public async Task<Result<List<TooltipDTO>>> GetTooltipListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetTooltipByIdAsync")]
public async Task<Result<TooltipUpdateModel>> GetTooltipByIdAsync(
    int tooltipId)
{
    throw new NotImplementedException();
}

[HttpGet("GetTooltipByIdExtendedAsync")]
public async Task<Result<TooltipDTO>> GetTooltipByIdExtendedAsync(
    int tooltipId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] TooltipInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] TooltipUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int tooltipId)
{
    throw new NotImplementedException();
}
