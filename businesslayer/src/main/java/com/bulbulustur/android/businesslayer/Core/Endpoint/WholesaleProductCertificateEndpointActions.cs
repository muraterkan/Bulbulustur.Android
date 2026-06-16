using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetWholesaleProductCertificateListAsync")]
public async Task<Result<List<WholesaleProductCertificateDTO>>> GetWholesaleProductCertificateListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductCertificateByIdAsync")]
public async Task<Result<WholesaleProductCertificateUpdateModel>> GetWholesaleProductCertificateByIdAsync(
    int wholesaleProductCertificateId)
{
    throw new NotImplementedException();
}

[HttpGet("GetWholesaleProductCertificateByIdExtendedAsync")]
public async Task<Result<WholesaleProductCertificateDTO>> GetWholesaleProductCertificateByIdExtendedAsync(
    int wholesaleProductCertificateId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] WholesaleProductCertificateInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] WholesaleProductCertificateUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int wholesaleProductCertificateId)
{
    throw new NotImplementedException();
}
