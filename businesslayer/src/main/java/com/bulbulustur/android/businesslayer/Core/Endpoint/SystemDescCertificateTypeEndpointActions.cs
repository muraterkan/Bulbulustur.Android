using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetSystemDescCertificateTypeListAsync")]
public async Task<Result<List<SystemDescCertificateTypeDTO>>> GetSystemDescCertificateTypeListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescCertificateTypeByIdAsync")]
public async Task<Result<SystemDescCertificateTypeUpdateModel>> GetSystemDescCertificateTypeByIdAsync(
    int systemDescCertificateTypeId)
{
    throw new NotImplementedException();
}

[HttpGet("GetSystemDescCertificateTypeByIdExtendedAsync")]
public async Task<Result<SystemDescCertificateTypeDTO>> GetSystemDescCertificateTypeByIdExtendedAsync(
    int systemDescCertificateTypeId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] SystemDescCertificateTypeInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] SystemDescCertificateTypeUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int systemDescCertificateTypeId)
{
    throw new NotImplementedException();
}
