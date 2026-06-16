using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductPropertyValueListAsync")]
public async Task<Result<List<ProductPropertyValueDTO>>> GetProductPropertyValueListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductPropertyValueByIdAsync")]
public async Task<Result<ProductPropertyValueUpdateModel>> GetProductPropertyValueByIdAsync(
    int productPropertyValueId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductPropertyValueByIdExtendedAsync")]
public async Task<Result<ProductPropertyValueDTO>> GetProductPropertyValueByIdExtendedAsync(
    int productPropertyValueId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductPropertyValueInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductPropertyValueUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productPropertyValueId)
{
    throw new NotImplementedException();
}
