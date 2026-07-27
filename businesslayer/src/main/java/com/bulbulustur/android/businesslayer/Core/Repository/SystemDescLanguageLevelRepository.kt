package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageLevelDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescLanguageLevelRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescLanguageLevelRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescLanguageLevelRepository {

    
override suspend fun GetSystemDescLanguageLevelsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescLanguageLevelDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "MasterData/GetSystemDescLanguageLevelsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescLanguageLevelByIdExtendedAsync(
        languageLevelId: Int
    ): Result<SystemDescLanguageLevelDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "MasterData/GetSystemDescLanguageLevelByIdExtendedAsync",
            query = "languageLevelId=$languageLevelId&systemDescLanguageLevelId=$languageLevelId"
        )
    }
}
