package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescLanguageLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescLanguageLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescLanguageLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescLanguageLanguageRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescLanguageLanguageRepository {

    override suspend fun GetSystemDescLanguageLanguageListAsync(): Result<List<SystemDescLanguageLanguageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescLanguageLanguageListAsync"
        )
    }

    override suspend fun GetSystemDescLanguageLanguageByIdAsync(
        systemDescLanguageLanguageId: Int
    ): Result<SystemDescLanguageLanguageUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescLanguageLanguageByIdAsync",
            query = "systemDescLanguageLanguageId=$systemDescLanguageLanguageId"
        )
    }

    override suspend fun GetSystemDescLanguageLanguageByIdExtendedAsync(
        systemDescLanguageLanguageId: Int
    ): Result<SystemDescLanguageLanguageDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescLanguageLanguageByIdExtendedAsync",
            query = "systemDescLanguageLanguageId=$systemDescLanguageLanguageId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescLanguageLanguageInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescLanguageLanguageUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescLanguageLanguageId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescLanguageLanguageId=$systemDescLanguageLanguageId"
        )
    }
}