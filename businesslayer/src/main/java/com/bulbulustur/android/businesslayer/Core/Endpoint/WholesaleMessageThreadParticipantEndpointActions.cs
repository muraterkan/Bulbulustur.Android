using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleMessageThreadParticipantListAsync")]
public async Task<Result<List<WholesaleMessageThreadParticipantDTO>>> GetWholesaleMessageThreadParticipantListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleMessageThreadParticipantByIdAsync")]
public async Task<Result<WholesaleMessageThreadParticipantUpdateModel>> GetWholesaleMessageThreadParticipantByIdAsync(
    int wholesaleMessageThreadParticipantId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleMessageThreadParticipantByIdExtendedAsync")]
public async Task<Result<WholesaleMessageThreadParticipantDTO>> GetWholesaleMessageThreadParticipantByIdExtendedAsync(
    int wholesaleMessageThreadParticipantId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleMessageThreadParticipantInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleMessageThreadParticipantUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleMessageThreadParticipantId)
{
    throw new NotImplementedException();
}
