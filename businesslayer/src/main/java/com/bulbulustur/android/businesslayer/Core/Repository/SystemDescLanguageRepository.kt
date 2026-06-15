package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescLanguageRepository(
    private val apiClient: ApiClient
) : ISystemDescLanguageRepository {

    override suspend fun GetSystemDescLanguageListAsync(): Result<List<SystemDescLanguageDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescLanguageByIdAsync(
        systemDescLanguageId: Int
    ): Result<SystemDescLanguageUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescLanguageByIdExtendedAsync(
        systemDescLanguageId: Int
    ): Result<SystemDescLanguageDTO?> {
        TODO("Not implemented yet")
    }
}
