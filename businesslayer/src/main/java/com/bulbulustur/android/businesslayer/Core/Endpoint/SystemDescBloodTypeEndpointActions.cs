using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescBloodTypeListAsync")]
public async Task<Result<List<SystemDescBloodTypeDTO>>> GetSystemDescBloodTypeListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescBloodTypeByIdAsync")]
public async Task<Result<SystemDescBloodTypeUpdateModel>> GetSystemDescBloodTypeByIdAsync(
    int systemDescBloodTypeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescBloodTypeByIdExtendedAsync")]
public async Task<Result<SystemDescBloodTypeDTO>> GetSystemDescBloodTypeByIdExtendedAsync(
    int systemDescBloodTypeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescBloodTypeInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescBloodTypeUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescBloodTypeId)
{
    throw new NotImplementedException();
}
