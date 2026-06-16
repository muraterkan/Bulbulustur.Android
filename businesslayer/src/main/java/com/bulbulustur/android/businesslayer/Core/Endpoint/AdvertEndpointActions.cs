using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetAdvertListAsync")]
public async Task<Result<List<AdvertDTO>>> GetAdvertListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetAdvertByIdAsync")]
public async Task<Result<AdvertUpdateModel>> GetAdvertByIdAsync(
    int advertId)
{
    throw new NotImplementedException();
}

[HttpGet("GetAdvertByIdExtendedAsync")]
public async Task<Result<AdvertDTO>> GetAdvertByIdExtendedAsync(
    int advertId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] AdvertInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] AdvertUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int advertId)
{
    throw new NotImplementedException();
}
