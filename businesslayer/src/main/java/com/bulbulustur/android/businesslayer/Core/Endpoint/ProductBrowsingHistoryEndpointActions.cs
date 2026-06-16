using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductBrowsingHistoryListAsync")]
public async Task<Result<List<ProductBrowsingHistoryDTO>>> GetProductBrowsingHistoryListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductBrowsingHistoryByIdAsync")]
public async Task<Result<ProductBrowsingHistoryUpdateModel>> GetProductBrowsingHistoryByIdAsync(
    int productBrowsingHistoryId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductBrowsingHistoryByIdExtendedAsync")]
public async Task<Result<ProductBrowsingHistoryDTO>> GetProductBrowsingHistoryByIdExtendedAsync(
    int productBrowsingHistoryId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductBrowsingHistoryInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductBrowsingHistoryUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productBrowsingHistoryId)
{
    throw new NotImplementedException();
}
