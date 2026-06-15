package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.Util.Result
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDepartmentDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryDepartmentUpdateModel

interface IAddressCountryDepartmentRepository {

    suspend fun GetAddressCountryDepartmentListAsync(): Result<List<AddressCountryDepartmentDTO>>

    suspend fun GetAddressCountryDepartmentByIdAsync(
        addressCountryDepartmentId: Int
    ): Result<AddressCountryDepartmentUpdateModel?>

    suspend fun GetAddressCountryDepartmentByIdExtendedAsync(
        addressCountryDepartmentId: Int
    ): Result<AddressCountryDepartmentDTO?>
}
