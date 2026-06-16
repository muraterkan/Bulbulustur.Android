using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetPaymentAttemptListAsync")]
public async Task<Result<List<PaymentAttemptDTO>>> GetPaymentAttemptListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetPaymentAttemptByIdAsync")]
public async Task<Result<PaymentAttemptUpdateModel>> GetPaymentAttemptByIdAsync(
    int paymentAttemptId)
{
    throw new NotImplementedException();
}

[HttpGet("GetPaymentAttemptByIdExtendedAsync")]
public async Task<Result<PaymentAttemptDTO>> GetPaymentAttemptByIdExtendedAsync(
    int paymentAttemptId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] PaymentAttemptInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] PaymentAttemptUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int paymentAttemptId)
{
    throw new NotImplementedException();
}
