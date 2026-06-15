package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberAlarmListDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberAlarmListRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAlarmListUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class MemberAlarmListRepository(
    private val apiClient: ApiClient
) : IMemberAlarmListRepository {

    override suspend fun GetMemberAlarmListListAsync(): Result<List<MemberAlarmListDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberAlarmListByIdAsync(
        memberAlarmListId: Int
    ): Result<MemberAlarmListUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberAlarmListByIdExtendedAsync(
        memberAlarmListId: Int
    ): Result<MemberAlarmListDTO?> {
        TODO("Not implemented yet")
    }
}
