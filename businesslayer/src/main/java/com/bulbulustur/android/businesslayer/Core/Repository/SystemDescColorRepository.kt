package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescColorDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescColorRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescColorUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescColorRepository(
    private val apiClient: ApiClient
) : ISystemDescColorRepository {

    override suspend fun GetSystemDescColorListAsync(): Result<List<SystemDescColorDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescColorByIdAsync(
        systemDescColorId: Int
    ): Result<SystemDescColorUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescColorByIdExtendedAsync(
        systemDescColorId: Int
    ): Result<SystemDescColorDTO?> {
        TODO("Not implemented yet")
    }
}
