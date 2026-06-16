using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescColorListAsync")]
public async Task<Result<List<SystemDescColorDTO>>> GetSystemDescColorListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescColorByIdAsync")]
public async Task<Result<SystemDescColorUpdateModel>> GetSystemDescColorByIdAsync(
    int systemDescColorId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescColorByIdExtendedAsync")]
public async Task<Result<SystemDescColorDTO>> GetSystemDescColorByIdExtendedAsync(
    int systemDescColorId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescColorInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescColorUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescColorId)
{
    throw new NotImplementedException();
}
