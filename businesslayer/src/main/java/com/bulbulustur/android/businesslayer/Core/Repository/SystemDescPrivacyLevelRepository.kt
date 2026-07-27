package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPrivacyLevelDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescPrivacyLevelRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPrivacyLevelInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPrivacyLevelUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescPrivacyLevelRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescPrivacyLevelRepository {

    override suspend fun GetSystemDescPrivacyLevelsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescPrivacyLevelDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPrivacyLevelsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescPrivacyLevelByIdAsync(
        systemDescPrivacyLevelId: Int
    ): Result<SystemDescPrivacyLevelUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPrivacyLevelByIdAsync",
            query = "systemDescPrivacyLevelId=$systemDescPrivacyLevelId"
        )
    }

    override suspend fun GetSystemDescPrivacyLevelByIdExtendedAsync(
        languageId: Int,
        systemDescPrivacyLevelId: Int
    ): Result<SystemDescPrivacyLevelDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPrivacyLevelByIdExtendedAsync",
            query = "languageId=$languageId&systemDescPrivacyLevelId=$systemDescPrivacyLevelId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescPrivacyLevelInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescPrivacyLevelAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescPrivacyLevelUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescPrivacyLevelAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescPrivacyLevelId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescPrivacyLevelAsync",
            query = "systemDescPrivacyLevelId=$systemDescPrivacyLevelId"
        )
    }
}