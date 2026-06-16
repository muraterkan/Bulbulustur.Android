using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescBankListAsync")]
public async Task<Result<List<SystemDescBankDTO>>> GetSystemDescBankListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescBankByIdAsync")]
public async Task<Result<SystemDescBankUpdateModel>> GetSystemDescBankByIdAsync(
    int systemDescBankId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescBankByIdExtendedAsync")]
public async Task<Result<SystemDescBankDTO>> GetSystemDescBankByIdExtendedAsync(
    int systemDescBankId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescBankInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescBankUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescBankId)
{
    throw new NotImplementedException();
}
