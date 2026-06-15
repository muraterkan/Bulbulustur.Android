package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageMemberDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleMessageMemberRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageMemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleMessageMemberRepository(
    private val apiClient: ApiClient
) : IWholesaleMessageMemberRepository {

    override suspend fun GetWholesaleMessageMemberListAsync(): Result<List<WholesaleMessageMemberDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleMessageMemberByIdAsync(
        wholesaleMessageMemberId: Int
    ): Result<WholesaleMessageMemberUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleMessageMemberByIdExtendedAsync(
        wholesaleMessageMemberId: Int
    ): Result<WholesaleMessageMemberDTO?> {
        TODO("Not implemented yet")
    }
}
