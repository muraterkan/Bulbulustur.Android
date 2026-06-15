package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderStoreStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescOrderStoreStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderStoreStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescOrderStoreStatusRepository(
    private val apiClient: ApiClient
) : ISystemDescOrderStoreStatusRepository {

    override suspend fun GetSystemDescOrderStoreStatusListAsync(): Result<List<SystemDescOrderStoreStatusDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescOrderStoreStatusByIdAsync(
        systemDescOrderStoreStatusId: Int
    ): Result<SystemDescOrderStoreStatusUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescOrderStoreStatusByIdExtendedAsync(
        systemDescOrderStoreStatusId: Int
    ): Result<SystemDescOrderStoreStatusDTO?> {
        TODO("Not implemented yet")
    }
}
