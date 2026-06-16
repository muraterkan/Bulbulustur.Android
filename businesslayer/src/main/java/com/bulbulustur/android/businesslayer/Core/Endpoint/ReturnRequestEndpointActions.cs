using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetReturnRequestListAsync")]
public async Task<Result<List<ReturnRequestDTO>>> GetReturnRequestListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetReturnRequestByIdAsync")]
public async Task<Result<ReturnRequestUpdateModel>> GetReturnRequestByIdAsync(
    int returnRequestId)
{
    throw new NotImplementedException();
}

[HttpGet("GetReturnRequestByIdExtendedAsync")]
public async Task<Result<ReturnRequestDTO>> GetReturnRequestByIdExtendedAsync(
    int returnRequestId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ReturnRequestInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ReturnRequestUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int returnRequestId)
{
    throw new NotImplementedException();
}
