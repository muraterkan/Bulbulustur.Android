using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescGenderListAsync")]
public async Task<Result<List<SystemDescGenderDTO>>> GetSystemDescGenderListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescGenderByIdAsync")]
public async Task<Result<SystemDescGenderUpdateModel>> GetSystemDescGenderByIdAsync(
    int systemDescGenderId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescGenderByIdExtendedAsync")]
public async Task<Result<SystemDescGenderDTO>> GetSystemDescGenderByIdExtendedAsync(
    int systemDescGenderId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescGenderInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescGenderUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescGenderId)
{
    throw new NotImplementedException();
}
