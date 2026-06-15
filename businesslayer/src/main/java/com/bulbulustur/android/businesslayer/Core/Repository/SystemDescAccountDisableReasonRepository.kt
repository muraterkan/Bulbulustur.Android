package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAccountDisableReasonDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescAccountDisableReasonRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescAccountDisableReasonUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescAccountDisableReasonRepository(
    private val apiClient: ApiClient
) : ISystemDescAccountDisableReasonRepository {

    override suspend fun GetSystemDescAccountDisableReasonListAsync(): Result<List<SystemDescAccountDisableReasonDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescAccountDisableReasonByIdAsync(
        systemDescAccountDisableReasonId: Int
    ): Result<SystemDescAccountDisableReasonUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescAccountDisableReasonByIdExtendedAsync(
        systemDescAccountDisableReasonId: Int
    ): Result<SystemDescAccountDisableReasonDTO?> {
        TODO("Not implemented yet")
    }
}
