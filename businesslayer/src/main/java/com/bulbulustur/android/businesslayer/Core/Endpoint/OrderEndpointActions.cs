using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetOrderListAsync")]
public async Task<Result<List<OrderDTO>>> GetOrderListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetOrderByIdAsync")]
public async Task<Result<OrderUpdateModel>> GetOrderByIdAsync(
    int orderId)
{
    throw new NotImplementedException();
}

[HttpGet("GetOrderByIdExtendedAsync")]
public async Task<Result<OrderDTO>> GetOrderByIdExtendedAsync(
    int orderId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] OrderInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] OrderUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int orderId)
{
    throw new NotImplementedException();
}
