package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescLanguageRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescLanguageRepository {

    override suspend fun GetSystemDescLanguagesAsync(languageId: Int, count: Int): Result<List<SystemDescLanguageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "MasterData/GetSystemDescLanguagesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescLanguageByIdAsync(systemDescLanguageId: Int): Result<SystemDescLanguageUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "SystemDescLanguage/GetSystemDescLanguageByIdAsync",
            query = "systemDescLanguageId=$systemDescLanguageId"
        )
    }

    override suspend fun GetSystemDescLanguageByIdExtendedAsync(
        languageId: Int,
        systemDescLanguageId: Int
    ): Result<SystemDescLanguageDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "SystemDescLanguage/GetSystemDescLanguageByIdExtendedAsync",
            query = "languageId=$languageId&systemDescLanguageId=$systemDescLanguageId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescLanguageInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "SystemDescLanguage/SystemDescLanguageInsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescLanguageUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "SystemDescLanguage/SystemDescLanguageUpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescLanguageId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "SystemDescLanguage/SystemDescLanguageDelete",
            query = "systemDescLanguageId=$systemDescLanguageId"
        )
    }
}