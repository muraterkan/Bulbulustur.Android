using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCampaignDealsOfTheDayListAsync")]
public async Task<Result<List<CampaignDealsOfTheDayDTO>>> GetCampaignDealsOfTheDayListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCampaignDealsOfTheDayByIdAsync")]
public async Task<Result<CampaignDealsOfTheDayUpdateModel>> GetCampaignDealsOfTheDayByIdAsync(
    int campaignDealsOfTheDayId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCampaignDealsOfTheDayByIdExtendedAsync")]
public async Task<Result<CampaignDealsOfTheDayDTO>> GetCampaignDealsOfTheDayByIdExtendedAsync(
    int campaignDealsOfTheDayId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CampaignDealsOfTheDayInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CampaignDealsOfTheDayUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int campaignDealsOfTheDayId)
{
    throw new NotImplementedException();
}
