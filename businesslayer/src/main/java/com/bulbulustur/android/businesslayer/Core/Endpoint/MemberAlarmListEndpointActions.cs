using Bulbulustur.Web.Core.BusinessLayer.DTO;
using Bulbulustur.Web.Core.BusinessLayer.Models.InsertModels;
using Bulbulustur.Web.Core.BusinessLayer.Models.UpdateModels;
using Bulbulustur.Web.Core.BusinessLayer.Util;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

[HttpGet("GetMemberAlarmListListAsync")]
public async Task<Result<List<MemberAlarmListDTO>>> GetMemberAlarmListListAsync()
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberAlarmListByIdAsync")]
public async Task<Result<MemberAlarmListUpdateModel>> GetMemberAlarmListByIdAsync(
    int memberAlarmListId)
{
    throw new NotImplementedException();
}

[HttpGet("GetMemberAlarmListByIdExtendedAsync")]
public async Task<Result<MemberAlarmListDTO>> GetMemberAlarmListByIdExtendedAsync(
    int memberAlarmListId)
{
    throw new NotImplementedException();
}

[HttpPost("InsertAsync")]
public async Task<Result> InsertAsync(
    [FromBody] MemberAlarmListInsertModel model)
{
    throw new NotImplementedException();
}

[HttpPost("UpdateAsync")]
public async Task<Result> UpdateAsync(
    [FromBody] MemberAlarmListUpdateModel model)
{
    throw new NotImplementedException();
}

[HttpPost("DeleteAsync")]
public async Task<Result> DeleteAsync(
    int memberAlarmListId)
{
    throw new NotImplementedException();
}
