using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetAdvertProductListAsync")]
public async Task<Result<List<AdvertProductDTO>>> GetAdvertProductListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetAdvertProductByIdAsync")]
public async Task<Result<AdvertProductUpdateModel>> GetAdvertProductByIdAsync(
    int advertProductId)
{
    throw new NotImplementedException();
}

[HttpGet("GetAdvertProductByIdExtendedAsync")]
public async Task<Result<AdvertProductDTO>> GetAdvertProductByIdExtendedAsync(
    int advertProductId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] AdvertProductInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] AdvertProductUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int advertProductId)
{
    throw new NotImplementedException();
}
