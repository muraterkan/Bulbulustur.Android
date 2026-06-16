using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleProductListAsync")]
public async Task<Result<List<WholesaleProductDTO>>> GetWholesaleProductListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductByIdAsync")]
public async Task<Result<WholesaleProductUpdateModel>> GetWholesaleProductByIdAsync(
    int wholesaleProductId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductByIdExtendedAsync")]
public async Task<Result<WholesaleProductDTO>> GetWholesaleProductByIdExtendedAsync(
    int wholesaleProductId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleProductInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleProductUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleProductId)
{
    throw new NotImplementedException();
}
