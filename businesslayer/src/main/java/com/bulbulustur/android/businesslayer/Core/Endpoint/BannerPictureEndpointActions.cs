using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetBannerPictureListAsync")]
public async Task<Result<List<BannerPictureDTO>>> GetBannerPictureListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetBannerPictureByIdAsync")]
public async Task<Result<BannerPictureUpdateModel>> GetBannerPictureByIdAsync(
    int bannerPictureId)
{
    throw new NotImplementedException();
}

[HttpGet("GetBannerPictureByIdExtendedAsync")]
public async Task<Result<BannerPictureDTO>> GetBannerPictureByIdExtendedAsync(
    int bannerPictureId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] BannerPictureInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] BannerPictureUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int bannerPictureId)
{
    throw new NotImplementedException();
}
