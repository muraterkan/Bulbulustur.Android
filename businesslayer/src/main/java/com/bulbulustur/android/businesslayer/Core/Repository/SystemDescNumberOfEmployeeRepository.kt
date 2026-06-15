package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescNumberOfEmployeeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescNumberOfEmployeeRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescNumberOfEmployeeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescNumberOfEmployeeRepository(
    private val apiClient: ApiClient
) : ISystemDescNumberOfEmployeeRepository {

    override suspend fun GetSystemDescNumberOfEmployeeListAsync(): Result<List<SystemDescNumberOfEmployeeDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescNumberOfEmployeeByIdAsync(
        systemDescNumberOfEmployeeId: Int
    ): Result<SystemDescNumberOfEmployeeUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescNumberOfEmployeeByIdExtendedAsync(
        systemDescNumberOfEmployeeId: Int
    ): Result<SystemDescNumberOfEmployeeDTO?> {
        TODO("Not implemented yet")
    }
}
