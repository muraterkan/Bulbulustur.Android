using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetReturnRequestPictureListAsync")]
public async Task<Result<List<ReturnRequestPictureDTO>>> GetReturnRequestPictureListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetReturnRequestPictureByIdAsync")]
public async Task<Result<ReturnRequestPictureUpdateModel>> GetReturnRequestPictureByIdAsync(
    int returnRequestPictureId)
{
    throw new NotImplementedException();
}

[HttpGet("GetReturnRequestPictureByIdExtendedAsync")]
public async Task<Result<ReturnRequestPictureDTO>> GetReturnRequestPictureByIdExtendedAsync(
    int returnRequestPictureId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ReturnRequestPictureInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ReturnRequestPictureUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int returnRequestPictureId)
{
    throw new NotImplementedException();
}
