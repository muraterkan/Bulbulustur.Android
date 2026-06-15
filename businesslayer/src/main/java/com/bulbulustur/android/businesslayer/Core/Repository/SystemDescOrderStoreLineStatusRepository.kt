package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderStoreLineStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescOrderStoreLineStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderStoreLineStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescOrderStoreLineStatusRepository(
    private val apiClient: ApiClient
) : ISystemDescOrderStoreLineStatusRepository {

    override suspend fun GetSystemDescOrderStoreLineStatusListAsync(): Result<List<SystemDescOrderStoreLineStatusDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescOrderStoreLineStatusByIdAsync(
        systemDescOrderStoreLineStatusId: Int
    ): Result<SystemDescOrderStoreLineStatusUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescOrderStoreLineStatusByIdExtendedAsync(
        systemDescOrderStoreLineStatusId: Int
    ): Result<SystemDescOrderStoreLineStatusDTO?> {
        TODO("Not implemented yet")
    }
}
