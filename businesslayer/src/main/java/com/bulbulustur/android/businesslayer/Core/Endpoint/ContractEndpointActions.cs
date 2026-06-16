using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetContractListAsync")]
public async Task<Result<List<ContractDTO>>> GetContractListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetContractByIdAsync")]
public async Task<Result<ContractUpdateModel>> GetContractByIdAsync(
    int contractId)
{
    throw new NotImplementedException();
}

[HttpGet("GetContractByIdExtendedAsync")]
public async Task<Result<ContractDTO>> GetContractByIdExtendedAsync(
    int contractId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ContractInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ContractUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int contractId)
{
    throw new NotImplementedException();
}
