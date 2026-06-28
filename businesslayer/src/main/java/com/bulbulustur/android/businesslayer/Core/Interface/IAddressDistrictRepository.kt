package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AddressDistrictDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressDistrictInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressDistrictUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IAddressDistrictRepository {

    suspend fun GetAddressDistrictsAsync(
        countryId: Int,
        countryStateId: Int,
        countryDepartmentId: Int?,
        cityId: Int,
        count: Int
    ): Result<List<AddressDistrictDTO>>

    suspend fun GetAddressDistrictListAsync():
            Result<List<AddressDistrictDTO>>

    suspend fun GetAddressDistrictByIdAsync(
        addressDistrictId: Int
    ): Result<AddressDistrictUpdateModel?>

    suspend fun GetAddressDistrictByIdExtendedAsync(
        addressDistrictId: Int
    ): Result<AddressDistrictDTO?>

    suspend fun InsertAsync(
        model: AddressDistrictInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: AddressDistrictUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        addressDistrictId: Int
    ): Result<Unit>
}