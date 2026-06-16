using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetReviewPictureListAsync")]
public async Task<Result<List<ReviewPictureDTO>>> GetReviewPictureListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetReviewPictureByIdAsync")]
public async Task<Result<ReviewPictureUpdateModel>> GetReviewPictureByIdAsync(
    int reviewPictureId)
{
    throw new NotImplementedException();
}

[HttpGet("GetReviewPictureByIdExtendedAsync")]
public async Task<Result<ReviewPictureDTO>> GetReviewPictureByIdExtendedAsync(
    int reviewPictureId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ReviewPictureInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ReviewPictureUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int reviewPictureId)
{
    throw new NotImplementedException();
}
