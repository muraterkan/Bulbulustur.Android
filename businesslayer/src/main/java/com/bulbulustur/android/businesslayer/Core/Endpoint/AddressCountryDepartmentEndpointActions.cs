using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetAddressCountryDepartmentListAsync")]
public async Task<Result<List<AddressCountryDepartmentDTO>>> GetAddressCountryDepartmentListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetAddressCountryDepartmentByIdAsync")]
public async Task<Result<AddressCountryDepartmentUpdateModel>> GetAddressCountryDepartmentByIdAsync(
    int addressCountryDepartmentId)
{
    throw new NotImplementedException();
}

[HttpGet("GetAddressCountryDepartmentByIdExtendedAsync")]
public async Task<Result<AddressCountryDepartmentDTO>> GetAddressCountryDepartmentByIdExtendedAsync(
    int addressCountryDepartmentId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] AddressCountryDepartmentInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] AddressCountryDepartmentUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int addressCountryDepartmentId)
{
    throw new NotImplementedException();
}
