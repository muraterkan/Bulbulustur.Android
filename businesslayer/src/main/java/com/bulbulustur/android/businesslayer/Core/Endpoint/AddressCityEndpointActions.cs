using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetAddressCityListAsync")]
public async Task<Result<List<AddressCityDTO>>> GetAddressCityListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetAddressCityByIdAsync")]
public async Task<Result<AddressCityUpdateModel>> GetAddressCityByIdAsync(
    int addressCityId)
{
    throw new NotImplementedException();
}

[HttpGet("GetAddressCityByIdExtendedAsync")]
public async Task<Result<AddressCityDTO>> GetAddressCityByIdExtendedAsync(
    int addressCityId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] AddressCityInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] AddressCityUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int addressCityId)
{
    throw new NotImplementedException();
}
