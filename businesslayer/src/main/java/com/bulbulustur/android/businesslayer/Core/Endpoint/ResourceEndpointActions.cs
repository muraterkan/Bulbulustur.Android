using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetResourceListAsync")]
public async Task<Result<List<ResourceDTO>>> GetResourceListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetResourceByIdAsync")]
public async Task<Result<ResourceUpdateModel>> GetResourceByIdAsync(
    int resourceId)
{
    throw new NotImplementedException();
}

[HttpGet("GetResourceByIdExtendedAsync")]
public async Task<Result<ResourceDTO>> GetResourceByIdExtendedAsync(
    int resourceId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ResourceInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ResourceUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int resourceId)
{
    throw new NotImplementedException();
}
