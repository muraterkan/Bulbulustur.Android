using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetTutorialListAsync")]
public async Task<Result<List<TutorialDTO>>> GetTutorialListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetTutorialByIdAsync")]
public async Task<Result<TutorialUpdateModel>> GetTutorialByIdAsync(
    int tutorialId)
{
    throw new NotImplementedException();
}

[HttpGet("GetTutorialByIdExtendedAsync")]
public async Task<Result<TutorialDTO>> GetTutorialByIdExtendedAsync(
    int tutorialId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] TutorialInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] TutorialUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int tutorialId)
{
    throw new NotImplementedException();
}
