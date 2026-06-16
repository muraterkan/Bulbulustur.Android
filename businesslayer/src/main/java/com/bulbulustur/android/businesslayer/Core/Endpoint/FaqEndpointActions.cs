using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetFaqListAsync")]
public async Task<Result<List<FaqDTO>>> GetFaqListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetFaqByIdAsync")]
public async Task<Result<FaqUpdateModel>> GetFaqByIdAsync(
    int faqId)
{
    throw new NotImplementedException();
}

[HttpGet("GetFaqByIdExtendedAsync")]
public async Task<Result<FaqDTO>> GetFaqByIdExtendedAsync(
    int faqId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] FaqInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] FaqUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int faqId)
{
    throw new NotImplementedException();
}
