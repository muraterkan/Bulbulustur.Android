package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescHomeSocialLevelDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescHomeSocialLevelRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescHomeSocialLevelInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescHomeSocialLevelUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescHomeSocialLevelRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescHomeSocialLevelRepository {

    override suspend fun GetSystemDescHomeSocialLevelsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescHomeSocialLevelDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescHomeSocialLevelsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescHomeSocialLevelByIdAsync(
        systemDescHomeSocialLevelId: Int
    ): Result<SystemDescHomeSocialLevelUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescHomeSocialLevelByIdAsync",
            query = "systemDescHomeSocialLevelId=$systemDescHomeSocialLevelId"
        )
    }

    override suspend fun GetSystemDescHomeSocialLevelByIdExtendedAsync(
        languageId: Int,
        systemDescHomeSocialLevelId: Int
    ): Result<SystemDescHomeSocialLevelDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescHomeSocialLevelByIdExtendedAsync",
            query = "languageId=$languageId&systemDescHomeSocialLevelId=$systemDescHomeSocialLevelId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescHomeSocialLevelInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescHomeSocialLevelAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescHomeSocialLevelUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescHomeSocialLevelAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescHomeSocialLevelId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescHomeSocialLevelAsync",
            query = "systemDescHomeSocialLevelId=$systemDescHomeSocialLevelId"
        )
    }
}