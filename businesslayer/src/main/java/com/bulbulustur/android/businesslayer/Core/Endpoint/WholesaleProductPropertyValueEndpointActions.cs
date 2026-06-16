using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleProductPropertyValueListAsync")]
public async Task<Result<List<WholesaleProductPropertyValueDTO>>> GetWholesaleProductPropertyValueListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductPropertyValueByIdAsync")]
public async Task<Result<WholesaleProductPropertyValueUpdateModel>> GetWholesaleProductPropertyValueByIdAsync(
    int wholesaleProductPropertyValueId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductPropertyValueByIdExtendedAsync")]
public async Task<Result<WholesaleProductPropertyValueDTO>> GetWholesaleProductPropertyValueByIdExtendedAsync(
    int wholesaleProductPropertyValueId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleProductPropertyValueInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleProductPropertyValueUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleProductPropertyValueId)
{
    throw new NotImplementedException();
}
