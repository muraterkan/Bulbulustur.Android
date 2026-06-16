using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCampaignConditionListAsync")]
public async Task<Result<List<CampaignConditionDTO>>> GetCampaignConditionListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCampaignConditionByIdAsync")]
public async Task<Result<CampaignConditionUpdateModel>> GetCampaignConditionByIdAsync(
    int campaignConditionId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCampaignConditionByIdExtendedAsync")]
public async Task<Result<CampaignConditionDTO>> GetCampaignConditionByIdExtendedAsync(
    int campaignConditionId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CampaignConditionInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CampaignConditionUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int campaignConditionId)
{
    throw new NotImplementedException();
}
