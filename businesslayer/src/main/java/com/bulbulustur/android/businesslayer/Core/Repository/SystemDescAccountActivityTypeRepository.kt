package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAccountActivityTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescAccountActivityTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescAccountActivityTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescAccountActivityTypeRepository(
    private val apiClient: ApiClient
) : ISystemDescAccountActivityTypeRepository {

    override suspend fun GetSystemDescAccountActivityTypeListAsync(): Result<List<SystemDescAccountActivityTypeDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescAccountActivityTypeByIdAsync(
        systemDescAccountActivityTypeId: Int
    ): Result<SystemDescAccountActivityTypeUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescAccountActivityTypeByIdExtendedAsync(
        systemDescAccountActivityTypeId: Int
    ): Result<SystemDescAccountActivityTypeDTO?> {
        TODO("Not implemented yet")
    }
}
