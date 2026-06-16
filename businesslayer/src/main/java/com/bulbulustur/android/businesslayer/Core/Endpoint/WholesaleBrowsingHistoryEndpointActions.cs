using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleBrowsingHistoryListAsync")]
public async Task<Result<List<WholesaleBrowsingHistoryDTO>>> GetWholesaleBrowsingHistoryListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleBrowsingHistoryByIdAsync")]
public async Task<Result<WholesaleBrowsingHistoryUpdateModel>> GetWholesaleBrowsingHistoryByIdAsync(
    int wholesaleBrowsingHistoryId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleBrowsingHistoryByIdExtendedAsync")]
public async Task<Result<WholesaleBrowsingHistoryDTO>> GetWholesaleBrowsingHistoryByIdExtendedAsync(
    int wholesaleBrowsingHistoryId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleBrowsingHistoryInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleBrowsingHistoryUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleBrowsingHistoryId)
{
    throw new NotImplementedException();
}
