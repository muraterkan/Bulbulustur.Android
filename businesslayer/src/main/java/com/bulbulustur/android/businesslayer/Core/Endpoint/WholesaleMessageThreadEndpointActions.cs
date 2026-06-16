using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleMessageThreadListAsync")]
public async Task<Result<List<WholesaleMessageThreadDTO>>> GetWholesaleMessageThreadListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleMessageThreadByIdAsync")]
public async Task<Result<WholesaleMessageThreadUpdateModel>> GetWholesaleMessageThreadByIdAsync(
    int wholesaleMessageThreadId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleMessageThreadByIdExtendedAsync")]
public async Task<Result<WholesaleMessageThreadDTO>> GetWholesaleMessageThreadByIdExtendedAsync(
    int wholesaleMessageThreadId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleMessageThreadInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleMessageThreadUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleMessageThreadId)
{
    throw new NotImplementedException();
}
