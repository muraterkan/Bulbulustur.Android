package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryStateDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCountryStateInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryStateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IAddressCountryStateRepository {

    suspend fun GetCountryStatesAsync(
        countryId: Int,
        count: Int
    ): Result<List<AddressCountryStateDTO>>

    suspend fun GetAddressCountryStateListAsync():
            Result<List<AddressCountryStateDTO>>

    suspend fun GetAddressCountryStateByIdAsync(
        addressCountryStateId: Int
    ): Result<AddressCountryStateUpdateModel?>

    suspend fun GetAddressCountryStateByIdExtendedAsync(
        addressCountryStateId: Int
    ): Result<AddressCountryStateDTO?>

    suspend fun InsertAsync(
        model: AddressCountryStateInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: AddressCountryStateUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        addressCountryStateId: Int
    ): Result<Unit>
}