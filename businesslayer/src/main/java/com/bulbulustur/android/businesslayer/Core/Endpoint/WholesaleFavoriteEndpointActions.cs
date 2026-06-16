using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleFavoriteListAsync")]
public async Task<Result<List<WholesaleFavoriteDTO>>> GetWholesaleFavoriteListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleFavoriteByIdAsync")]
public async Task<Result<WholesaleFavoriteUpdateModel>> GetWholesaleFavoriteByIdAsync(
    int wholesaleFavoriteId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleFavoriteByIdExtendedAsync")]
public async Task<Result<WholesaleFavoriteDTO>> GetWholesaleFavoriteByIdExtendedAsync(
    int wholesaleFavoriteId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleFavoriteInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleFavoriteUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleFavoriteId)
{
    throw new NotImplementedException();
}
