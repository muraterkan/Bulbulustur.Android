package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberAlarmListDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAlarmListUpdateModel

interface IMemberAlarmListRepository {

    suspend fun GetMemberAlarmListListAsync(): Result<List<MemberAlarmListDTO>>

    suspend fun GetMemberAlarmListByIdAsync(
        memberAlarmListId: Int
    ): Result<MemberAlarmListUpdateModel?>

    suspend fun GetMemberAlarmListByIdExtendedAsync(
        memberAlarmListId: Int
    ): Result<MemberAlarmListDTO?>
}
