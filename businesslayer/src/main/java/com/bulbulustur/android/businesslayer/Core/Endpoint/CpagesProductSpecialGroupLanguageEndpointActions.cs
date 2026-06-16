using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetCpagesProductSpecialGroupLanguageListAsync")]
public async Task<Result<List<CpagesProductSpecialGroupLanguageDTO>>> GetCpagesProductSpecialGroupLanguageListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetCpagesProductSpecialGroupLanguageByIdAsync")]
public async Task<Result<CpagesProductSpecialGroupLanguageUpdateModel>> GetCpagesProductSpecialGroupLanguageByIdAsync(
    int cpagesProductSpecialGroupLanguageId)
{
    throw new NotImplementedException();
}

[HttpGet("GetCpagesProductSpecialGroupLanguageByIdExtendedAsync")]
public async Task<Result<CpagesProductSpecialGroupLanguageDTO>> GetCpagesProductSpecialGroupLanguageByIdExtendedAsync(
    int cpagesProductSpecialGroupLanguageId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] CpagesProductSpecialGroupLanguageInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] CpagesProductSpecialGroupLanguageUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int cpagesProductSpecialGroupLanguageId)
{
    throw new NotImplementedException();
}
