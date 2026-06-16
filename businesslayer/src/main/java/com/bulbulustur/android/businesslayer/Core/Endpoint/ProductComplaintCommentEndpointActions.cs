using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetProductComplaintCommentListAsync")]
public async Task<Result<List<ProductComplaintCommentDTO>>> GetProductComplaintCommentListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetProductComplaintCommentByIdAsync")]
public async Task<Result<ProductComplaintCommentUpdateModel>> GetProductComplaintCommentByIdAsync(
    int productComplaintCommentId)
{
    throw new NotImplementedException();
}

[HttpGet("GetProductComplaintCommentByIdExtendedAsync")]
public async Task<Result<ProductComplaintCommentDTO>> GetProductComplaintCommentByIdExtendedAsync(
    int productComplaintCommentId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] ProductComplaintCommentInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] ProductComplaintCommentUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int productComplaintCommentId)
{
    throw new NotImplementedException();
}
