package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescProductDenyReasonDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescProductDenyReasonRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescProductDenyReasonUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescProductDenyReasonRepository(
    private val apiClient: ApiClient
) : ISystemDescProductDenyReasonRepository {

    override suspend fun GetSystemDescProductDenyReasonListAsync(): Result<List<SystemDescProductDenyReasonDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescProductDenyReasonByIdAsync(
        systemDescProductDenyReasonId: Int
    ): Result<SystemDescProductDenyReasonUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescProductDenyReasonByIdExtendedAsync(
        systemDescProductDenyReasonId: Int
    ): Result<SystemDescProductDenyReasonDTO?> {
        TODO("Not implemented yet")
    }
}
