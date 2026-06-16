using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescTradeTermListAsync")]
public async Task<Result<List<SystemDescTradeTermDTO>>> GetSystemDescTradeTermListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescTradeTermByIdAsync")]
public async Task<Result<SystemDescTradeTermUpdateModel>> GetSystemDescTradeTermByIdAsync(
    int systemDescTradeTermId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescTradeTermByIdExtendedAsync")]
public async Task<Result<SystemDescTradeTermDTO>> GetSystemDescTradeTermByIdExtendedAsync(
    int systemDescTradeTermId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescTradeTermInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescTradeTermUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescTradeTermId)
{
    throw new NotImplementedException();
}
