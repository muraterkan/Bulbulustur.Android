package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAccountClosureReasonDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescAccountClosureReasonRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescAccountClosureReasonUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescAccountClosureReasonRepository(
    private val apiClient: ApiClient
) : ISystemDescAccountClosureReasonRepository {

    override suspend fun GetSystemDescAccountClosureReasonListAsync(): Result<List<SystemDescAccountClosureReasonDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescAccountClosureReasonByIdAsync(
        systemDescAccountClosureReasonId: Int
    ): Result<SystemDescAccountClosureReasonUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescAccountClosureReasonByIdExtendedAsync(
        systemDescAccountClosureReasonId: Int
    ): Result<SystemDescAccountClosureReasonDTO?> {
        TODO("Not implemented yet")
    }
}
