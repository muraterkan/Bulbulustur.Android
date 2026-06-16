using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCpagesProductSpecialListAsync")]
public async Task<Result<List<CpagesProductSpecialDTO>>> GetCpagesProductSpecialListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCpagesProductSpecialByIdAsync")]
public async Task<Result<CpagesProductSpecialUpdateModel>> GetCpagesProductSpecialByIdAsync(
    int cpagesProductSpecialId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCpagesProductSpecialByIdExtendedAsync")]
public async Task<Result<CpagesProductSpecialDTO>> GetCpagesProductSpecialByIdExtendedAsync(
    int cpagesProductSpecialId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CpagesProductSpecialInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CpagesProductSpecialUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int cpagesProductSpecialId)
{
    throw new NotImplementedException();
}
