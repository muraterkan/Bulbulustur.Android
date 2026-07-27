package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescContactRequestStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescContactRequestStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescContactRequestStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescContactRequestStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescContactRequestStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescContactRequestStatusRepository {

    override suspend fun GetSystemDescContactRequestStatusesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescContactRequestStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescContactRequestStatusesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescContactRequestStatusByIdAsync(
        systemDescContactRequestStatusId: Int
    ): Result<SystemDescContactRequestStatusUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescContactRequestStatusByIdAsync",
            query = "systemDescContactRequestStatusId=$systemDescContactRequestStatusId"
        )
    }

    override suspend fun GetSystemDescContactRequestStatusByIdExtendedAsync(
        languageId: Int,
        systemDescContactRequestStatusId: Int
    ): Result<SystemDescContactRequestStatusDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescContactRequestStatusByIdExtendedAsync",
            query = "languageId=$languageId&systemDescContactRequestStatusId=$systemDescContactRequestStatusId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescContactRequestStatusInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertSystemDescContactRequestStatusAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescContactRequestStatusUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdateSystemDescContactRequestStatusAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescContactRequestStatusId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeleteSystemDescContactRequestStatusAsync",
            query = "systemDescContactRequestStatusId=$systemDescContactRequestStatusId"
        )
    }
}