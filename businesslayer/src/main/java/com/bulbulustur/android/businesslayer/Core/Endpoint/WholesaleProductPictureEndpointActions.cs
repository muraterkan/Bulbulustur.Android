using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleProductPictureListAsync")]
public async Task<Result<List<WholesaleProductPictureDTO>>> GetWholesaleProductPictureListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductPictureByIdAsync")]
public async Task<Result<WholesaleProductPictureUpdateModel>> GetWholesaleProductPictureByIdAsync(
    int wholesaleProductPictureId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductPictureByIdExtendedAsync")]
public async Task<Result<WholesaleProductPictureDTO>> GetWholesaleProductPictureByIdExtendedAsync(
    int wholesaleProductPictureId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleProductPictureInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleProductPictureUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleProductPictureId)
{
    throw new NotImplementedException();
}
