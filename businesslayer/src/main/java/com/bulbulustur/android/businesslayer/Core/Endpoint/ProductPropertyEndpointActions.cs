using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductPropertyListAsync")]
public async Task<Result<List<ProductPropertyDTO>>> GetProductPropertyListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductPropertyByIdAsync")]
public async Task<Result<ProductPropertyUpdateModel>> GetProductPropertyByIdAsync(
    int productPropertyId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductPropertyByIdExtendedAsync")]
public async Task<Result<ProductPropertyDTO>> GetProductPropertyByIdExtendedAsync(
    int productPropertyId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductPropertyInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductPropertyUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productPropertyId)
{
    throw new NotImplementedException();
}
