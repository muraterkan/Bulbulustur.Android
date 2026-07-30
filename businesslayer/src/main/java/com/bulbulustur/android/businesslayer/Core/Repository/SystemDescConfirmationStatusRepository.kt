package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescConfirmationStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescConfirmationStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescConfirmationStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescConfirmationStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescConfirmationStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescConfirmationStatusRepository {

    override suspend fun GetSystemDescConfirmationStatusesAsync(
        count: Int
    ): Result<List<SystemDescConfirmationStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescConfirmationStatusesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetSystemDescConfirmationStatusByIdAsync(
        confirmationStatusId: Int
    ): Result<SystemDescConfirmationStatusDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescConfirmationStatusByIdAsync",
            query = "confirmationStatusId=$confirmationStatusId"
        )
    }

    override suspend fun GetSystemDescConfirmationStatusByIdExtendedAsync(
        confirmationStatusId: Int
    ): Result<SystemDescConfirmationStatusDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescConfirmationStatusByIdExtendedAsync",
            query = "confirmationStatusId=$confirmationStatusId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescConfirmationStatusInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "InsertSystemDescConfirmationStatusAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescConfirmationStatusUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "UpdateSystemDescConfirmationStatusAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        confirmationStatusId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "DeleteSystemDescConfirmationStatusAsync",
            query = "confirmationStatusId=$confirmationStatusId"
        )
    }
}