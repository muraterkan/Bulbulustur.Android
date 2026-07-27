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

    override suspend fun GetSystemDescReturnRequestStatusesAsync(
        count: Int
    ): Result<List<SystemDescReturnRequestStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescReturnRequestStatusesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetSystemDescReturnRequestStatusByIdAsync(
        systemDescReturnRequestStatusId: Int
    ): Result<SystemDescReturnRequestStatusUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescReturnRequestStatusByIdAsync",
            query = "systemDescReturnRequestStatusId=$systemDescReturnRequestStatusId"
        )
    }

    override suspend fun GetSystemDescReturnRequestStatusByIdExtendedAsync(
        systemDescReturnRequestStatusId: Int
    ): Result<SystemDescReturnRequestStatusDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescReturnRequestStatusByIdExtendedAsync",
            query = "systemDescReturnRequestStatusId=$systemDescReturnRequestStatusId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescReturnRequestStatusInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "InsertSystemDescReturnRequestStatusAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescReturnRequestStatusUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "UpdateSystemDescReturnRequestStatusAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescReturnRequestStatusId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "DeleteSystemDescReturnRequestStatusAsync",
            query = "systemDescReturnRequestStatusId=$systemDescReturnRequestStatusId"
        )
    }
}