package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberBlockDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberBlockRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberBlockUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class MemberBlockRepository(
    private val apiClient: ApiClient
) : IMemberBlockRepository {

    override suspend fun GetMemberBlockListAsync(): Result<List<MemberBlockDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberBlockByIdAsync(
        memberBlockId: Int
    ): Result<MemberBlockUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberBlockByIdExtendedAsync(
        memberBlockId: Int
    ): Result<MemberBlockDTO?> {
        TODO("Not implemented yet")
    }
}
