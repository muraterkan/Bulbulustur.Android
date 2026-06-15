package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReturnRequestStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescReturnRequestStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescReturnRequestStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescReturnRequestStatusRepository(
    private val apiClient: ApiClient
) : ISystemDescReturnRequestStatusRepository {

    override suspend fun GetSystemDescReturnRequestStatusListAsync(): Result<List<SystemDescReturnRequestStatusDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescReturnRequestStatusByIdAsync(
        systemDescReturnRequestStatusId: Int
    ): Result<SystemDescReturnRequestStatusUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescReturnRequestStatusByIdExtendedAsync(
        systemDescReturnRequestStatusId: Int
    ): Result<SystemDescReturnRequestStatusDTO?> {
        TODO("Not implemented yet")
    }
}
