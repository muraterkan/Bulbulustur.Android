package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderStoreLineStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescOrderStoreLineStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescOrderStoreLineStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderStoreLineStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescOrderStoreLineStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescOrderStoreLineStatusRepository {

    override suspend fun GetSystemDescOrderStoreLineStatusesAsync(
        count: Int
    ): Result<List<SystemDescOrderStoreLineStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescOrderStoreLineStatusesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetSystemDescOrderStoreLineStatusByIdAsync(
        systemDescOrderStoreLineStatusId: Int
    ): Result<SystemDescOrderStoreLineStatusUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescOrderStoreLineStatusByIdAsync",
            query = "systemDescOrderStoreLineStatusId=$systemDescOrderStoreLineStatusId"
        )
    }

    override suspend fun GetSystemDescOrderStoreLineStatusByIdExtendedAsync(
        systemDescOrderStoreLineStatusId: Int
    ): Result<SystemDescOrderStoreLineStatusDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescOrderStoreLineStatusByIdExtendedAsync",
            query = "systemDescOrderStoreLineStatusId=$systemDescOrderStoreLineStatusId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescOrderStoreLineStatusInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "InsertSystemDescOrderStoreLineStatusAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescOrderStoreLineStatusUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "UpdateSystemDescOrderStoreLineStatusAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescOrderStoreLineStatusId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "DeleteSystemDescOrderStoreLineStatusAsync",
            query = "systemDescOrderStoreLineStatusId=$systemDescOrderStoreLineStatusId"
        )
    }
}