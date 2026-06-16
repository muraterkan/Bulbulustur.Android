using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetAdvertSponsoredListAsync")]
public async Task<Result<List<AdvertSponsoredDTO>>> GetAdvertSponsoredListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetAdvertSponsoredByIdAsync")]
public async Task<Result<AdvertSponsoredUpdateModel>> GetAdvertSponsoredByIdAsync(
    int advertSponsoredId)
{
    throw new NotImplementedException();
}

[HttpGet("GetAdvertSponsoredByIdExtendedAsync")]
public async Task<Result<AdvertSponsoredDTO>> GetAdvertSponsoredByIdExtendedAsync(
    int advertSponsoredId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] AdvertSponsoredInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] AdvertSponsoredUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int advertSponsoredId)
{
    throw new NotImplementedException();
}
