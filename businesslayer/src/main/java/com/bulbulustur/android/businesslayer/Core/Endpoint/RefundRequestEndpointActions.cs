using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetRefundRequestListAsync")]
public async Task<Result<List<RefundRequestDTO>>> GetRefundRequestListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetRefundRequestByIdAsync")]
public async Task<Result<RefundRequestUpdateModel>> GetRefundRequestByIdAsync(
    int refundRequestId)
{
    throw new NotImplementedException();
}

[HttpGet("GetRefundRequestByIdExtendedAsync")]
public async Task<Result<RefundRequestDTO>> GetRefundRequestByIdExtendedAsync(
    int refundRequestId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] RefundRequestInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] RefundRequestUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int refundRequestId)
{
    throw new NotImplementedException();
}
