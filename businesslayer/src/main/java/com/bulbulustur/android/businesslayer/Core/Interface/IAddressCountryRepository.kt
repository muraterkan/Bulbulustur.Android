package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryUpdateModel

interface IAddressCountryRepository {

    suspend fun GetAddressCountryListAsync(): Result<List<AddressCountryDTO>>

    suspend fun GetAddressCountryByIdAsync(
        addressCountryId: Int
    ): Result<AddressCountryUpdateModel?>

    suspend fun GetAddressCountryByIdExtendedAsync(
        addressCountryId: Int
    ): Result<AddressCountryDTO?>
}
