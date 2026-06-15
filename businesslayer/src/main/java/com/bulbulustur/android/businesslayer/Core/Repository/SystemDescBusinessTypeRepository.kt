package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBusinessTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescBusinessTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescBusinessTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescBusinessTypeRepository(
    private val apiClient: ApiClient
) : ISystemDescBusinessTypeRepository {

    override suspend fun GetSystemDescBusinessTypeListAsync(): Result<List<SystemDescBusinessTypeDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescBusinessTypeByIdAsync(
        systemDescBusinessTypeId: Int
    ): Result<SystemDescBusinessTypeUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescBusinessTypeByIdExtendedAsync(
        systemDescBusinessTypeId: Int
    ): Result<SystemDescBusinessTypeDTO?> {
        TODO("Not implemented yet")
    }
}
