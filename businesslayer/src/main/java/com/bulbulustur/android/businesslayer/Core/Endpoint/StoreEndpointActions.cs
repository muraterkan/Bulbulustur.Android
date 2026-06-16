using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetStoreListAsync")]
public async Task<Result<List<StoreDTO>>> GetStoreListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetStoreByIdAsync")]
public async Task<Result<StoreUpdateModel>> GetStoreByIdAsync(
    int storeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetStoreByIdExtendedAsync")]
public async Task<Result<StoreDTO>> GetStoreByIdExtendedAsync(
    int storeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] StoreInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] StoreUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int storeId)
{
    throw new NotImplementedException();
}
