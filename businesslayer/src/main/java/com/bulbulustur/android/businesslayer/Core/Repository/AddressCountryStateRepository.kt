package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryStateDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCountryStateRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryStateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class AddressCountryStateRepository(
    private val apiClient: ApiClient
) : IAddressCountryStateRepository {

    override suspend fun GetAddressCountryStateListAsync(): Result<List<AddressCountryStateDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAddressCountryStateByIdAsync(
        addressCountryStateId: Int
    ): Result<AddressCountryStateUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAddressCountryStateByIdExtendedAsync(
        addressCountryStateId: Int
    ): Result<AddressCountryStateDTO?> {
        TODO("Not implemented yet")
    }
}
