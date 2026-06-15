package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDepartmentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCountryDepartmentRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryDepartmentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class AddressCountryDepartmentRepository(
    private val apiClient: ApiClient
) : IAddressCountryDepartmentRepository {

    override suspend fun GetAddressCountryDepartmentListAsync(): Result<List<AddressCountryDepartmentDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAddressCountryDepartmentByIdAsync(
        addressCountryDepartmentId: Int
    ): Result<AddressCountryDepartmentUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAddressCountryDepartmentByIdExtendedAsync(
        addressCountryDepartmentId: Int
    ): Result<AddressCountryDepartmentDTO?> {
        TODO("Not implemented yet")
    }
}
