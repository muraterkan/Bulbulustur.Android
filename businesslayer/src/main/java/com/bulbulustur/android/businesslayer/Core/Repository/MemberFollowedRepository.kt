package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberFollowedDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberFollowedRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberFollowedUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class MemberFollowedRepository(
    private val apiClient: ApiClient
) : IMemberFollowedRepository {

    override suspend fun GetMemberFollowedListAsync(): Result<List<MemberFollowedDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberFollowedByIdAsync(
        memberFollowedId: Int
    ): Result<MemberFollowedUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberFollowedByIdExtendedAsync(
        memberFollowedId: Int
    ): Result<MemberFollowedDTO?> {
        TODO("Not implemented yet")
    }
}
