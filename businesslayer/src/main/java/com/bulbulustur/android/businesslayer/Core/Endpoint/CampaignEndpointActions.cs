using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCampaignListAsync")]
public async Task<Result<List<CampaignDTO>>> GetCampaignListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCampaignByIdAsync")]
public async Task<Result<CampaignUpdateModel>> GetCampaignByIdAsync(
    int campaignId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCampaignByIdExtendedAsync")]
public async Task<Result<CampaignDTO>> GetCampaignByIdExtendedAsync(
    int campaignId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CampaignInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CampaignUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int campaignId)
{
    throw new NotImplementedException();
}
