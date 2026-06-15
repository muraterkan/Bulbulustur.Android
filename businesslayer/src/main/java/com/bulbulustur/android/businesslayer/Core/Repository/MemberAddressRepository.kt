package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberAddressDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberAddressRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class MemberAddressRepository(
    private val apiClient: ApiClient
) : IMemberAddressRepository {

    override suspend fun GetMemberAddressListAsync(): Result<List<MemberAddressDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberAddressByIdAsync(
        memberAddressId: Int
    ): Result<MemberAddressUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberAddressByIdExtendedAsync(
        memberAddressId: Int
    ): Result<MemberAddressDTO?> {
        TODO("Not implemented yet")
    }
}
