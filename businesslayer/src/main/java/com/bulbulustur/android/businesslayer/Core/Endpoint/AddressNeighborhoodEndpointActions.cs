using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetAddressNeighborhoodListAsync")]
public async Task<Result<List<AddressNeighborhoodDTO>>> GetAddressNeighborhoodListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetAddressNeighborhoodByIdAsync")]
public async Task<Result<AddressNeighborhoodUpdateModel>> GetAddressNeighborhoodByIdAsync(
    int addressNeighborhoodId)
{
    throw new NotImplementedException();
}

[HttpGet("GetAddressNeighborhoodByIdExtendedAsync")]
public async Task<Result<AddressNeighborhoodDTO>> GetAddressNeighborhoodByIdExtendedAsync(
    int addressNeighborhoodId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] AddressNeighborhoodInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] AddressNeighborhoodUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int addressNeighborhoodId)
{
    throw new NotImplementedException();
}
