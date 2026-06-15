package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryStateDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryStateUpdateModel

interface IAddressCountryStateRepository {

    suspend fun GetAddressCountryStateListAsync(): Result<List<AddressCountryStateDTO>>

    suspend fun GetAddressCountryStateByIdAsync(
        addressCountryStateId: Int
    ): Result<AddressCountryStateUpdateModel?>

    suspend fun GetAddressCountryStateByIdExtendedAsync(
        addressCountryStateId: Int
    ): Result<AddressCountryStateDTO?>
}
