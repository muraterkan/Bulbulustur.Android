using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetHelpRelatedTopicListAsync")]
public async Task<Result<List<HelpRelatedTopicDTO>>> GetHelpRelatedTopicListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpRelatedTopicByIdAsync")]
public async Task<Result<HelpRelatedTopicUpdateModel>> GetHelpRelatedTopicByIdAsync(
    int helpRelatedTopicId)
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpRelatedTopicByIdExtendedAsync")]
public async Task<Result<HelpRelatedTopicDTO>> GetHelpRelatedTopicByIdExtendedAsync(
    int helpRelatedTopicId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] HelpRelatedTopicInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] HelpRelatedTopicUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int helpRelatedTopicId)
{
    throw new NotImplementedException();
}
