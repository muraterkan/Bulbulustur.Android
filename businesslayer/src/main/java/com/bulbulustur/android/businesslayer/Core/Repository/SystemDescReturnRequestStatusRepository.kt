package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReturnRequestStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescReturnRequestStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescReturnRequestStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescReturnRequestStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescReturnRequestStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescReturnRequestStatusRepository {

    override suspend fun GetSystemDescReturnRequestStatusListAsync(): Result<List<SystemDescReturnRequestStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescReturnRequestStatusListAsync"
        )
    }

    override suspend fun GetSystemDescReturnRequestStatusByIdAsync(
        systemDescReturnRequestStatusId: Int
    ): Result<SystemDescReturnRequestStatusUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescReturnRequestStatusByIdAsync",
            query = "systemDescReturnRequestStatusId=$systemDescReturnRequestStatusId"
        )
    }

    override suspend fun GetSystemDescReturnRequestStatusByIdExtendedAsync(
        systemDescReturnRequestStatusId: Int
    ): Result<SystemDescReturnRequestStatusDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescReturnRequestStatusByIdExtendedAsync",
            query = "systemDescReturnRequestStatusId=$systemDescReturnRequestStatusId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescReturnRequestStatusInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescReturnRequestStatusUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescReturnRequestStatusId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescReturnRequestStatusId=$systemDescReturnRequestStatusId"
        )
    }
}