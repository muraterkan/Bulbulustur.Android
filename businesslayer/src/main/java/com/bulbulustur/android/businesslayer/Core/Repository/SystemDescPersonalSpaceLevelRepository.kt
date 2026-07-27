package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPersonalSpaceLevelDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescPersonalSpaceLevelRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPersonalSpaceLevelInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPersonalSpaceLevelUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescPersonalSpaceLevelRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescPersonalSpaceLevelRepository {

    override suspend fun GetSystemDescPersonalSpaceLevelsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescPersonalSpaceLevelDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPersonalSpaceLevelsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescPersonalSpaceLevelByIdAsync(
        systemDescPersonalSpaceLevelId: Int
    ): Result<SystemDescPersonalSpaceLevelUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPersonalSpaceLevelByIdAsync",
            query = "systemDescPersonalSpaceLevelId=$systemDescPersonalSpaceLevelId"
        )
    }

    override suspend fun GetSystemDescPersonalSpaceLevelByIdExtendedAsync(
        languageId: Int,
        systemDescPersonalSpaceLevelId: Int
    ): Result<SystemDescPersonalSpaceLevelDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPersonalSpaceLevelByIdExtendedAsync",
            query = "languageId=$languageId&systemDescPersonalSpaceLevelId=$systemDescPersonalSpaceLevelId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescPersonalSpaceLevelInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescPersonalSpaceLevelAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescPersonalSpaceLevelUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescPersonalSpaceLevelAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescPersonalSpaceLevelId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescPersonalSpaceLevelAsync",
            query = "systemDescPersonalSpaceLevelId=$systemDescPersonalSpaceLevelId"
        )
    }
}