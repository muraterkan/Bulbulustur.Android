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

    override suspend fun GetSystemDescOrderStoreStatusListAsync(): Result<List<SystemDescOrderStoreStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescOrderStoreStatusListAsync"
        )
    }

    override suspend fun GetSystemDescOrderStoreStatusByIdAsync(
        systemDescOrderStoreStatusId: Int
    ): Result<SystemDescOrderStoreStatusUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescOrderStoreStatusByIdAsync",
            query = "systemDescOrderStoreStatusId=$systemDescOrderStoreStatusId"
        )
    }

    override suspend fun GetSystemDescOrderStoreStatusByIdExtendedAsync(
        systemDescOrderStoreStatusId: Int
    ): Result<SystemDescOrderStoreStatusDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescOrderStoreStatusByIdExtendedAsync",
            query = "systemDescOrderStoreStatusId=$systemDescOrderStoreStatusId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescOrderStoreStatusInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescOrderStoreStatusUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescOrderStoreStatusId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescOrderStoreStatusId=$systemDescOrderStoreStatusId"
        )
    }
}