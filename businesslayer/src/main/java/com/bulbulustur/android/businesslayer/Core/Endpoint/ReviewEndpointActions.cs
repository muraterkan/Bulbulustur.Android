using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetReviewListAsync")]
public async Task<Result<List<ReviewDTO>>> GetReviewListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetReviewByIdAsync")]
public async Task<Result<ReviewUpdateModel>> GetReviewByIdAsync(
    int reviewId)
{
    throw new NotImplementedException();
}

[HttpGet("GetReviewByIdExtendedAsync")]
public async Task<Result<ReviewDTO>> GetReviewByIdExtendedAsync(
    int reviewId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ReviewInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ReviewUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int reviewId)
{
    throw new NotImplementedException();
}
