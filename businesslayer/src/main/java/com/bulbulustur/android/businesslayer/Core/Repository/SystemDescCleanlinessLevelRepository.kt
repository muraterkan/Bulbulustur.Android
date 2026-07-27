package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCleanlinessLevelDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCleanlinessLevelRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCleanlinessLevelInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCleanlinessLevelUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescCleanlinessLevelRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescCleanlinessLevelRepository {

    override suspend fun GetSystemDescCleanlinessLevelsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescCleanlinessLevelDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCleanlinessLevelsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescCleanlinessLevelByIdAsync(
        systemDescCleanlinessLevelId: Int
    ): Result<SystemDescCleanlinessLevelUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCleanlinessLevelByIdAsync",
            query = "systemDescCleanlinessLevelId=$systemDescCleanlinessLevelId"
        )
    }

    override suspend fun GetSystemDescCleanlinessLevelByIdExtendedAsync(
        languageId: Int,
        systemDescCleanlinessLevelId: Int
    ): Result<SystemDescCleanlinessLevelDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCleanlinessLevelByIdExtendedAsync",
            query = "languageId=$languageId&systemDescCleanlinessLevelId=$systemDescCleanlinessLevelId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescCleanlinessLevelInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescCleanlinessLevelAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescCleanlinessLevelUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescCleanlinessLevelAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescCleanlinessLevelId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescCleanlinessLevelAsync",
            query = "systemDescCleanlinessLevelId=$systemDescCleanlinessLevelId"
        )
    }
}