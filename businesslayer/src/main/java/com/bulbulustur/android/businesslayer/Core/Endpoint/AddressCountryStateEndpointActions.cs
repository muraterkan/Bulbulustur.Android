using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetAddressCountryStateListAsync")]
public async Task<Result<List<AddressCountryStateDTO>>> GetAddressCountryStateListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetAddressCountryStateByIdAsync")]
public async Task<Result<AddressCountryStateUpdateModel>> GetAddressCountryStateByIdAsync(
    int addressCountryStateId)
{
    throw new NotImplementedException();
}

[HttpGet("GetAddressCountryStateByIdExtendedAsync")]
public async Task<Result<AddressCountryStateDTO>> GetAddressCountryStateByIdExtendedAsync(
    int addressCountryStateId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] AddressCountryStateInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] AddressCountryStateUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int addressCountryStateId)
{
    throw new NotImplementedException();
}
