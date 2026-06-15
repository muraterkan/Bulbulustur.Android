package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberLoginActivityDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberLoginActivityRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberLoginActivityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class MemberLoginActivityRepository(
    private val apiClient: ApiClient
) : IMemberLoginActivityRepository {

    override suspend fun GetMemberLoginActivityListAsync(): Result<List<MemberLoginActivityDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberLoginActivityByIdAsync(
        memberLoginActivityId: Int
    ): Result<MemberLoginActivityUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberLoginActivityByIdExtendedAsync(
        memberLoginActivityId: Int
    ): Result<MemberLoginActivityDTO?> {
        TODO("Not implemented yet")
    }
}
