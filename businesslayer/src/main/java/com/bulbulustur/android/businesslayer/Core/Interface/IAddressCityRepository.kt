package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCityDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCityInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IAddressCityRepository {

    suspend fun GetAddressCitiesAsync(
        countryId: Int,
        count: Int
    ): Result<List<AddressCityDTO>>

    suspend fun GetAddressCityListAsync():
            Result<List<AddressCityDTO>>

    suspend fun GetAddressCityByIdAsync(
        addressCityId: Int
    ): Result<AddressCityUpdateModel?>

    suspend fun GetAddressCityByIdExtendedAsync(
        addressCityId: Int
    ): Result<AddressCityDTO?>

    suspend fun InsertAsync(
        model: AddressCityInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: AddressCityUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        addressCityId: Int
    ): Result<Unit>
}