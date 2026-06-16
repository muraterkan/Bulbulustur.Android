using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetBannerListAsync")]
public async Task<Result<List<BannerDTO>>> GetBannerListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetBannerByIdAsync")]
public async Task<Result<BannerUpdateModel>> GetBannerByIdAsync(
    int bannerId)
{
    throw new NotImplementedException();
}

[HttpGet("GetBannerByIdExtendedAsync")]
public async Task<Result<BannerDTO>> GetBannerByIdExtendedAsync(
    int bannerId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] BannerInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] BannerUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int bannerId)
{
    throw new NotImplementedException();
}
