package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSharedAreaUsageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescSharedAreaUsageRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescSharedAreaUsageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescSharedAreaUsageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescSharedAreaUsageRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescSharedAreaUsageRepository {

    override suspend fun GetSystemDescSharedAreaUsagesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescSharedAreaUsageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescSharedAreaUsagesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescSharedAreaUsageByIdAsync(
        systemDescSharedAreaUsageId: Int
    ): Result<SystemDescSharedAreaUsageUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescSharedAreaUsageByIdAsync",
            query = "systemDescSharedAreaUsageId=$systemDescSharedAreaUsageId"
        )
    }

    override suspend fun GetSystemDescSharedAreaUsageByIdExtendedAsync(
        languageId: Int,
        systemDescSharedAreaUsageId: Int
    ): Result<SystemDescSharedAreaUsageDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescSharedAreaUsageByIdExtendedAsync",
            query = "languageId=$languageId&systemDescSharedAreaUsageId=$systemDescSharedAreaUsageId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescSharedAreaUsageInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescSharedAreaUsageAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescSharedAreaUsageUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescSharedAreaUsageAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescSharedAreaUsageId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescSharedAreaUsageAsync",
            query = "systemDescSharedAreaUsageId=$systemDescSharedAreaUsageId"
        )
    }
}