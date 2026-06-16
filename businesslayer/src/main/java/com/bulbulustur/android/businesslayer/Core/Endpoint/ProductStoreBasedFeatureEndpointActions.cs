using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductStoreBasedFeatureListAsync")]
public async Task<Result<List<ProductStoreBasedFeatureDTO>>> GetProductStoreBasedFeatureListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductStoreBasedFeatureByIdAsync")]
public async Task<Result<ProductStoreBasedFeatureUpdateModel>> GetProductStoreBasedFeatureByIdAsync(
    int productStoreBasedFeatureId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductStoreBasedFeatureByIdExtendedAsync")]
public async Task<Result<ProductStoreBasedFeatureDTO>> GetProductStoreBasedFeatureByIdExtendedAsync(
    int productStoreBasedFeatureId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductStoreBasedFeatureInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductStoreBasedFeatureUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productStoreBasedFeatureId)
{
    throw new NotImplementedException();
}
