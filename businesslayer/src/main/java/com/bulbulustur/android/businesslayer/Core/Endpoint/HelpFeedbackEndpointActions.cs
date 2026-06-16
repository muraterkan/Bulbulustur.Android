using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetHelpFeedbackListAsync")]
public async Task<Result<List<HelpFeedbackDTO>>> GetHelpFeedbackListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpFeedbackByIdAsync")]
public async Task<Result<HelpFeedbackUpdateModel>> GetHelpFeedbackByIdAsync(
    int helpFeedbackId)
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpFeedbackByIdExtendedAsync")]
public async Task<Result<HelpFeedbackDTO>> GetHelpFeedbackByIdExtendedAsync(
    int helpFeedbackId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] HelpFeedbackInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] HelpFeedbackUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int helpFeedbackId)
{
    throw new NotImplementedException();
}
