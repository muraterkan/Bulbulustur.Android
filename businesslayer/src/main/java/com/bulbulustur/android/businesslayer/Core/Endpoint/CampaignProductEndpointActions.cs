using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCampaignProductListAsync")]
public async Task<Result<List<CampaignProductDTO>>> GetCampaignProductListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCampaignProductByIdAsync")]
public async Task<Result<CampaignProductUpdateModel>> GetCampaignProductByIdAsync(
    int campaignProductId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCampaignProductByIdExtendedAsync")]
public async Task<Result<CampaignProductDTO>> GetCampaignProductByIdExtendedAsync(
    int campaignProductId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CampaignProductInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CampaignProductUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int campaignProductId)
{
    throw new NotImplementedException();
}
