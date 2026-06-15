package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescOrderStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescOrderStatusRepository(
    private val apiClient: ApiClient
) : ISystemDescOrderStatusRepository {

    override suspend fun GetSystemDescOrderStatusListAsync(): Result<List<SystemDescOrderStatusDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescOrderStatusByIdAsync(
        systemDescOrderStatusId: Int
    ): Result<SystemDescOrderStatusUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescOrderStatusByIdExtendedAsync(
        systemDescOrderStatusId: Int
    ): Result<SystemDescOrderStatusDTO?> {
        TODO("Not implemented yet")
    }
}
