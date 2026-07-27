package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderStoreStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescOrderStoreStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescOrderStoreStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderStoreStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescOrderStoreStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescOrderStoreStatusRepository {

    override suspend fun GetSystemDescOrderStoreStatusesAsync(
        count: Int
    ): Result<List<SystemDescOrderStoreStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescOrderStoreStatusesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetSystemDescOrderStoreStatusByIdAsync(
        systemDescOrderStoreStatusId: Int
    ): Result<SystemDescOrderStoreStatusUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescOrderStoreStatusByIdAsync",
            query = "systemDescOrderStoreStatusId=$systemDescOrderStoreStatusId"
        )
    }

    override suspend fun GetSystemDescOrderStoreStatusByIdExtendedAsync(
        systemDescOrderStoreStatusId: Int
    ): Result<SystemDescOrderStoreStatusDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescOrderStoreStatusByIdExtendedAsync",
            query = "systemDescOrderStoreStatusId=$systemDescOrderStoreStatusId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescOrderStoreStatusInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "InsertSystemDescOrderStoreStatusAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescOrderStoreStatusUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "UpdateSystemDescOrderStoreStatusAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescOrderStoreStatusId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "DeleteSystemDescOrderStoreStatusAsync",
            query = "systemDescOrderStoreStatusId=$systemDescOrderStoreStatusId"
        )
    }
}