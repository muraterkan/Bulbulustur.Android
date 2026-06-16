using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCpagesProductSpecialGroupListAsync")]
public async Task<Result<List<CpagesProductSpecialGroupDTO>>> GetCpagesProductSpecialGroupListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCpagesProductSpecialGroupByIdAsync")]
public async Task<Result<CpagesProductSpecialGroupUpdateModel>> GetCpagesProductSpecialGroupByIdAsync(
    int cpagesProductSpecialGroupId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCpagesProductSpecialGroupByIdExtendedAsync")]
public async Task<Result<CpagesProductSpecialGroupDTO>> GetCpagesProductSpecialGroupByIdExtendedAsync(
    int cpagesProductSpecialGroupId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CpagesProductSpecialGroupInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CpagesProductSpecialGroupUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int cpagesProductSpecialGroupId)
{
    throw new NotImplementedException();
}
