using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetHelpPopularTopicListAsync")]
public async Task<Result<List<HelpPopularTopicDTO>>> GetHelpPopularTopicListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpPopularTopicByIdAsync")]
public async Task<Result<HelpPopularTopicUpdateModel>> GetHelpPopularTopicByIdAsync(
    int helpPopularTopicId)
{
    throw new NotImplementedException();
}

[HttpGet("GetHelpPopularTopicByIdExtendedAsync")]
public async Task<Result<HelpPopularTopicDTO>> GetHelpPopularTopicByIdExtendedAsync(
    int helpPopularTopicId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] HelpPopularTopicInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] HelpPopularTopicUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int helpPopularTopicId)
{
    throw new NotImplementedException();
}
