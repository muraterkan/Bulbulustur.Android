package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryTimezoneDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryTimezoneUpdateModel

interface IAddressCountryTimezoneRepository {

    suspend fun GetAddressCountryTimezoneListAsync(): Result<List<AddressCountryTimezoneDTO>>

    suspend fun GetAddressCountryTimezoneByIdAsync(
        addressCountryTimezoneId: Int
    ): Result<AddressCountryTimezoneUpdateModel?>

    suspend fun GetAddressCountryTimezoneByIdExtendedAsync(
        addressCountryTimezoneId: Int
    ): Result<AddressCountryTimezoneDTO?>
}
