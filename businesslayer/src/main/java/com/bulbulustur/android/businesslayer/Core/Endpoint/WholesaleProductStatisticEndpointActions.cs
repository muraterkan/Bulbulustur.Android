using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleProductStatisticListAsync")]
public async Task<Result<List<WholesaleProductStatisticDTO>>> GetWholesaleProductStatisticListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductStatisticByIdAsync")]
public async Task<Result<WholesaleProductStatisticUpdateModel>> GetWholesaleProductStatisticByIdAsync(
    int wholesaleProductStatisticId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductStatisticByIdExtendedAsync")]
public async Task<Result<WholesaleProductStatisticDTO>> GetWholesaleProductStatisticByIdExtendedAsync(
    int wholesaleProductStatisticId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleProductStatisticInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleProductStatisticUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleProductStatisticId)
{
    throw new NotImplementedException();
}
