using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSendedOfferListAsync")]
public async Task<Result<List<SendedOfferDTO>>> GetSendedOfferListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSendedOfferByIdAsync")]
public async Task<Result<SendedOfferUpdateModel>> GetSendedOfferByIdAsync(
    int sendedOfferId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSendedOfferByIdExtendedAsync")]
public async Task<Result<SendedOfferDTO>> GetSendedOfferByIdExtendedAsync(
    int sendedOfferId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SendedOfferInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SendedOfferUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int sendedOfferId)
{
    throw new NotImplementedException();
}
