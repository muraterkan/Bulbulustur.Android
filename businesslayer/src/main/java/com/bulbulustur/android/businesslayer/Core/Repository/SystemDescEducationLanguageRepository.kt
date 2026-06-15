package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescEducationLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescEducationLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescEducationLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescEducationLanguageRepository(
    private val apiClient: ApiClient
) : ISystemDescEducationLanguageRepository {

    override suspend fun GetSystemDescEducationLanguageListAsync(): Result<List<SystemDescEducationLanguageDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescEducationLanguageByIdAsync(
        systemDescEducationLanguageId: Int
    ): Result<SystemDescEducationLanguageUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescEducationLanguageByIdExtendedAsync(
        systemDescEducationLanguageId: Int
    ): Result<SystemDescEducationLanguageDTO?> {
        TODO("Not implemented yet")
    }
}
