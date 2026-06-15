package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCountryRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class AddressCountryRepository(
    private val apiClient: ApiClient
) : IAddressCountryRepository {

    override suspend fun GetAddressCountryListAsync(): Result<List<AddressCountryDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAddressCountryByIdAsync(
        addressCountryId: Int
    ): Result<AddressCountryUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAddressCountryByIdExtendedAsync(
        addressCountryId: Int
    ): Result<AddressCountryDTO?> {
        TODO("Not implemented yet")
    }
}
