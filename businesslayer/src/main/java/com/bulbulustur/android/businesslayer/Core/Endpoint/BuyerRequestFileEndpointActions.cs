using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetBuyerRequestFileListAsync")]
public async Task<Result<List<BuyerRequestFileDTO>>> GetBuyerRequestFileListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetBuyerRequestFileByIdAsync")]
public async Task<Result<BuyerRequestFileUpdateModel>> GetBuyerRequestFileByIdAsync(
    int buyerRequestFileId)
{
    throw new NotImplementedException();
}

[HttpGet("GetBuyerRequestFileByIdExtendedAsync")]
public async Task<Result<BuyerRequestFileDTO>> GetBuyerRequestFileByIdExtendedAsync(
    int buyerRequestFileId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] BuyerRequestFileInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] BuyerRequestFileUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int buyerRequestFileId)
{
    throw new NotImplementedException();
}
