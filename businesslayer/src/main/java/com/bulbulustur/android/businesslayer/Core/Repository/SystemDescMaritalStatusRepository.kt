package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMaritalStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescMaritalStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMaritalStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescMaritalStatusRepository(
    private val apiClient: ApiClient
) : ISystemDescMaritalStatusRepository {

    override suspend fun GetSystemDescMaritalStatusListAsync(): Result<List<SystemDescMaritalStatusDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescMaritalStatusByIdAsync(
        systemDescMaritalStatusId: Int
    ): Result<SystemDescMaritalStatusUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescMaritalStatusByIdExtendedAsync(
        systemDescMaritalStatusId: Int
    ): Result<SystemDescMaritalStatusDTO?> {
        TODO("Not implemented yet")
    }
}
