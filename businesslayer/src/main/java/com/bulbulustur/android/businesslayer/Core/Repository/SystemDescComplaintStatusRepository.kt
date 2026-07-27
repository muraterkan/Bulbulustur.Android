package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescComplaintStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescComplaintStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescComplaintStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescComplaintStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescComplaintStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescComplaintStatusRepository {

    override suspend fun GetSystemDescComplaintStatusesAsync(
        count: Int
    ): Result<List<SystemDescComplaintStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescComplaintStatusesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetSystemDescComplaintStatusByIdAsync(
        systemDescComplaintStatusId: Int
    ): Result<SystemDescComplaintStatusUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescComplaintStatusByIdAsync",
            query = "systemDescComplaintStatusId=$systemDescComplaintStatusId"
        )
    }

    override suspend fun GetSystemDescComplaintStatusByIdExtendedAsync(
        systemDescComplaintStatusId: Int
    ): Result<SystemDescComplaintStatusDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescComplaintStatusByIdExtendedAsync",
            query = "systemDescComplaintStatusId=$systemDescComplaintStatusId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescComplaintStatusInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "InsertSystemDescComplaintStatusAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescComplaintStatusUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "UpdateSystemDescComplaintStatusAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescComplaintStatusId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "DeleteSystemDescComplaintStatusAsync",
            query = "systemDescComplaintStatusId=$systemDescComplaintStatusId"
        )
    }
}