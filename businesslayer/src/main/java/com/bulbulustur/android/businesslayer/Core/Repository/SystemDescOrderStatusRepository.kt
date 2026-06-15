package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescOrderStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescOrderStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescOrderStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescOrderStatusRepository {

    override suspend fun GetSystemDescOrderStatusListAsync(): Result<List<SystemDescOrderStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescOrderStatusListAsync"
        )
    }

    override suspend fun GetSystemDescOrderStatusByIdAsync(
        systemDescOrderStatusId: Int
    ): Result<SystemDescOrderStatusUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescOrderStatusByIdAsync",
            query = "systemDescOrderStatusId=$systemDescOrderStatusId"
        )
    }

    override suspend fun GetSystemDescOrderStatusByIdExtendedAsync(
        systemDescOrderStatusId: Int
    ): Result<SystemDescOrderStatusDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescOrderStatusByIdExtendedAsync",
            query = "systemDescOrderStatusId=$systemDescOrderStatusId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescOrderStatusInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescOrderStatusUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescOrderStatusId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescOrderStatusId=$systemDescOrderStatusId"
        )
    }
}