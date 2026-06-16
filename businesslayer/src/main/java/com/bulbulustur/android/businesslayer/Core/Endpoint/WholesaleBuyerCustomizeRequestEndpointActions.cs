using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleBuyerCustomizeRequestListAsync")]
public async Task<Result<List<WholesaleBuyerCustomizeRequestDTO>>> GetWholesaleBuyerCustomizeRequestListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleBuyerCustomizeRequestByIdAsync")]
public async Task<Result<WholesaleBuyerCustomizeRequestUpdateModel>> GetWholesaleBuyerCustomizeRequestByIdAsync(
    int wholesaleBuyerCustomizeRequestId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleBuyerCustomizeRequestByIdExtendedAsync")]
public async Task<Result<WholesaleBuyerCustomizeRequestDTO>> GetWholesaleBuyerCustomizeRequestByIdExtendedAsync(
    int wholesaleBuyerCustomizeRequestId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleBuyerCustomizeRequestInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleBuyerCustomizeRequestUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleBuyerCustomizeRequestId)
{
    throw new NotImplementedException();
}
