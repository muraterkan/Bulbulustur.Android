using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetAssignedToSellerListAsync")]
public async Task<Result<List<AssignedToSellerDTO>>> GetAssignedToSellerListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetAssignedToSellerByIdAsync")]
public async Task<Result<AssignedToSellerUpdateModel>> GetAssignedToSellerByIdAsync(
    int assignedToSellerId)
{
    throw new NotImplementedException();
}

[HttpGet("GetAssignedToSellerByIdExtendedAsync")]
public async Task<Result<AssignedToSellerDTO>> GetAssignedToSellerByIdExtendedAsync(
    int assignedToSellerId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] AssignedToSellerInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] AssignedToSellerUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int assignedToSellerId)
{
    throw new NotImplementedException();
}
