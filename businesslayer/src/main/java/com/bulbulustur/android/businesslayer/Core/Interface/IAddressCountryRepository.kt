package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCountryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IAddressCountryRepository {

    suspend fun GetAddressCountriesAsync(
        languageId: Int,
        count: Int
    ): Result<List<AddressCountryDTO>>

    suspend fun GetAddressCountryListAsync():
            Result<List<AddressCountryDTO>>

    suspend fun GetAddressCountryByIdAsync(
        addressCountryId: Int
    ): Result<AddressCountryUpdateModel?>

    suspend fun GetAddressCountryByIdExtendedAsync(
        languageId: Int,
        addressCountryId: Int
    ): Result<AddressCountryDTO?>

    suspend fun InsertAsync(
        model: AddressCountryInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: AddressCountryUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        addressCountryId: Int
    ): Result<Unit>
}