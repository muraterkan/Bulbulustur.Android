using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleMessageListAsync")]
public async Task<Result<List<WholesaleMessageDTO>>> GetWholesaleMessageListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleMessageByIdAsync")]
public async Task<Result<WholesaleMessageUpdateModel>> GetWholesaleMessageByIdAsync(
    int wholesaleMessageId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleMessageByIdExtendedAsync")]
public async Task<Result<WholesaleMessageDTO>> GetWholesaleMessageByIdExtendedAsync(
    int wholesaleMessageId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleMessageInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleMessageUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleMessageId)
{
    throw new NotImplementedException();
}
