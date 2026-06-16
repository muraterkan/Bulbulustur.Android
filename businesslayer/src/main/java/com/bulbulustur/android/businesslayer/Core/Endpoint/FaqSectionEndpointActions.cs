using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetFaqSectionListAsync")]
public async Task<Result<List<FaqSectionDTO>>> GetFaqSectionListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetFaqSectionByIdAsync")]
public async Task<Result<FaqSectionUpdateModel>> GetFaqSectionByIdAsync(
    int faqSectionId)
{
    throw new NotImplementedException();
}

[HttpGet("GetFaqSectionByIdExtendedAsync")]
public async Task<Result<FaqSectionDTO>> GetFaqSectionByIdExtendedAsync(
    int faqSectionId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] FaqSectionInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] FaqSectionUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int faqSectionId)
{
    throw new NotImplementedException();
}
