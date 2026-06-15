package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReturnRequestReasonDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescReturnRequestReasonRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescReturnRequestReasonUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescReturnRequestReasonRepository(
    private val apiClient: ApiClient
) : ISystemDescReturnRequestReasonRepository {

    override suspend fun GetSystemDescReturnRequestReasonListAsync(): Result<List<SystemDescReturnRequestReasonDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescReturnRequestReasonByIdAsync(
        systemDescReturnRequestReasonId: Int
    ): Result<SystemDescReturnRequestReasonUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescReturnRequestReasonByIdExtendedAsync(
        systemDescReturnRequestReasonId: Int
    ): Result<SystemDescReturnRequestReasonDTO?> {
        TODO("Not implemented yet")
    }
}
