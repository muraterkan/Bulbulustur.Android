package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryTimezoneDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCountryTimezoneRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryTimezoneUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class AddressCountryTimezoneRepository(
    private val apiClient: ApiClient
) : IAddressCountryTimezoneRepository {

    override suspend fun GetAddressCountryTimezoneListAsync(): Result<List<AddressCountryTimezoneDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAddressCountryTimezoneByIdAsync(
        addressCountryTimezoneId: Int
    ): Result<AddressCountryTimezoneUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAddressCountryTimezoneByIdExtendedAsync(
        addressCountryTimezoneId: Int
    ): Result<AddressCountryTimezoneDTO?> {
        TODO("Not implemented yet")
    }
}
