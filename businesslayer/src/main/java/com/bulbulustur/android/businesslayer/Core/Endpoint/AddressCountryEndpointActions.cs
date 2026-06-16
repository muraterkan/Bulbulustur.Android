using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetAddressCountryListAsync")]
public async Task<Result<List<AddressCountryDTO>>> GetAddressCountryListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetAddressCountryByIdAsync")]
public async Task<Result<AddressCountryUpdateModel>> GetAddressCountryByIdAsync(
    int addressCountryId)
{
    throw new NotImplementedException();
}

[HttpGet("GetAddressCountryByIdExtendedAsync")]
public async Task<Result<AddressCountryDTO>> GetAddressCountryByIdExtendedAsync(
    int addressCountryId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] AddressCountryInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] AddressCountryUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int addressCountryId)
{
    throw new NotImplementedException();
}
