using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleProductRelatedListAsync")]
public async Task<Result<List<WholesaleProductRelatedDTO>>> GetWholesaleProductRelatedListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductRelatedByIdAsync")]
public async Task<Result<WholesaleProductRelatedUpdateModel>> GetWholesaleProductRelatedByIdAsync(
    int wholesaleProductRelatedId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductRelatedByIdExtendedAsync")]
public async Task<Result<WholesaleProductRelatedDTO>> GetWholesaleProductRelatedByIdExtendedAsync(
    int wholesaleProductRelatedId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleProductRelatedInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleProductRelatedUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleProductRelatedId)
{
    throw new NotImplementedException();
}
