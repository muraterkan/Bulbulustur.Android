using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetStoreAddressListAsync")]
public async Task<Result<List<StoreAddressDTO>>> GetStoreAddressListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetStoreAddressByIdAsync")]
public async Task<Result<StoreAddressUpdateModel>> GetStoreAddressByIdAsync(
    int storeAddressId)
{
    throw new NotImplementedException();
}

[HttpGet("GetStoreAddressByIdExtendedAsync")]
public async Task<Result<StoreAddressDTO>> GetStoreAddressByIdExtendedAsync(
    int storeAddressId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] StoreAddressInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] StoreAddressUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int storeAddressId)
{
    throw new NotImplementedException();
}
