package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberActivityDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberActivityRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberActivityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class MemberActivityRepository(
    private val apiClient: ApiClient
) : IMemberActivityRepository {

    override suspend fun GetMemberActivityListAsync(): Result<List<MemberActivityDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberActivityByIdAsync(
        memberActivityId: Int
    ): Result<MemberActivityUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberActivityByIdExtendedAsync(
        memberActivityId: Int
    ): Result<MemberActivityDTO?> {
        TODO("Not implemented yet")
    }
}
