package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberAlarmListDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAlarmListInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAlarmListUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberAlarmListRepository {

    suspend fun GetAccountAlarmLists(memberId: Int, count: Int): Result<List<MemberAlarmListDTO>>

    suspend fun GetAccountAlarmListByIdAsync(memberId: Int, memberAlarmListId: Int): Result<MemberAlarmListUpdateModel?>

    suspend fun GetAccountAlarmListByIdExtendedAsync(memberId: Int, memberAlarmListId: Int): Result<MemberAlarmListDTO?>

    suspend fun InsertAccountAlarmAsync(memberId: Int, model: MemberAlarmListInsertModel): Result<Unit>

    suspend fun UpdateAccountAlarmAsync(memberId: Int, model: MemberAlarmListUpdateModel): Result<Unit>

    suspend fun DeleteAccountAlarmAsync(memberId: Int, memberAlarmListId: Int): Result<Unit>
}