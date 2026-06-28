package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDepartmentDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCountryDepartmentInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryDepartmentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IAddressCountryDepartmentRepository {

    suspend fun GetCountryDepartmentsAsync(
        countryId: Int,
        countryStateId: Int,
        count: Int
    ): Result<List<AddressCountryDepartmentDTO>>

    suspend fun GetAddressCountryDepartmentListAsync():
            Result<List<AddressCountryDepartmentDTO>>

    suspend fun GetAddressCountryDepartmentByIdAsync(
        addressCountryDepartmentId: Int
    ): Result<AddressCountryDepartmentUpdateModel?>

    suspend fun GetAddressCountryDepartmentByIdExtendedAsync(
        addressCountryDepartmentId: Int
    ): Result<AddressCountryDepartmentDTO?>

    suspend fun InsertAsync(
        model: AddressCountryDepartmentInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: AddressCountryDepartmentUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        addressCountryDepartmentId: Int
    ): Result<Unit>
}