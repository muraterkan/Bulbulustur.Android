using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescStoreAddressTypeListAsync")]
public async Task<Result<List<SystemDescStoreAddressTypeDTO>>> GetSystemDescStoreAddressTypeListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescStoreAddressTypeByIdAsync")]
public async Task<Result<SystemDescStoreAddressTypeUpdateModel>> GetSystemDescStoreAddressTypeByIdAsync(
    int systemDescStoreAddressTypeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescStoreAddressTypeByIdExtendedAsync")]
public async Task<Result<SystemDescStoreAddressTypeDTO>> GetSystemDescStoreAddressTypeByIdExtendedAsync(
    int systemDescStoreAddressTypeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescStoreAddressTypeInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescStoreAddressTypeUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescStoreAddressTypeId)
{
    throw new NotImplementedException();
}
