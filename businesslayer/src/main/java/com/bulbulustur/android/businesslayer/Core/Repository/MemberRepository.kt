package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class MemberRepository(
    private val apiClient: ApiClient
) : IMemberRepository {

    override suspend fun GetMemberListAsync(): Result<List<MemberDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberByIdAsync(
        memberId: Int
    ): Result<MemberUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberByIdExtendedAsync(
        memberId: Int
    ): Result<MemberDTO?> {
        TODO("Not implemented yet")
    }
}
