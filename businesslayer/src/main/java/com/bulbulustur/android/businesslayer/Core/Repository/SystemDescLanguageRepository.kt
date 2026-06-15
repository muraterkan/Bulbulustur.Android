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

    override suspend fun GetSystemDescLanguageListAsync(): Result<List<SystemDescLanguageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescLanguageListAsync"
        )
    }

    override suspend fun GetSystemDescLanguageByIdAsync(
        systemDescLanguageId: Int
    ): Result<SystemDescLanguageUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescLanguageByIdAsync",
            query = "systemDescLanguageId=$systemDescLanguageId"
        )
    }

    override suspend fun GetSystemDescLanguageByIdExtendedAsync(
        systemDescLanguageId: Int
    ): Result<SystemDescLanguageDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescLanguageByIdExtendedAsync",
            query = "systemDescLanguageId=$systemDescLanguageId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescLanguageInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescLanguageUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescLanguageId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescLanguageId=$systemDescLanguageId"
        )
    }
}