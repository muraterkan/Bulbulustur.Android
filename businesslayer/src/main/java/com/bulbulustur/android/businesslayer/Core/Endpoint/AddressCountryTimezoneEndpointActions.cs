using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetAddressCountryTimezoneListAsync")]
public async Task<Result<List<AddressCountryTimezoneDTO>>> GetAddressCountryTimezoneListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetAddressCountryTimezoneByIdAsync")]
public async Task<Result<AddressCountryTimezoneUpdateModel>> GetAddressCountryTimezoneByIdAsync(
    int addressCountryTimezoneId)
{
    throw new NotImplementedException();
}

[HttpGet("GetAddressCountryTimezoneByIdExtendedAsync")]
public async Task<Result<AddressCountryTimezoneDTO>> GetAddressCountryTimezoneByIdExtendedAsync(
    int addressCountryTimezoneId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] AddressCountryTimezoneInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] AddressCountryTimezoneUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int addressCountryTimezoneId)
{
    throw new NotImplementedException();
}
