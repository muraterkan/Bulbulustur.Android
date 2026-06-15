package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAccountDisableReasonDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescAccountDisableReasonRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescAccountDisableReasonInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescAccountDisableReasonUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescAccountDisableReasonRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescAccountDisableReasonRepository {

    override suspend fun GetSystemDescAccountDisableReasonListAsync(): Result<List<SystemDescAccountDisableReasonDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescAccountDisableReasonListAsync"
        )
    }

    override suspend fun GetSystemDescAccountDisableReasonByIdAsync(
        systemDescAccountDisableReasonId: Int
    ): Result<SystemDescAccountDisableReasonUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescAccountDisableReasonByIdAsync",
            query = "systemDescAccountDisableReasonId=$systemDescAccountDisableReasonId"
        )
    }

    override suspend fun GetSystemDescAccountDisableReasonByIdExtendedAsync(
        systemDescAccountDisableReasonId: Int
    ): Result<SystemDescAccountDisableReasonDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescAccountDisableReasonByIdExtendedAsync",
            query = "systemDescAccountDisableReasonId=$systemDescAccountDisableReasonId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescAccountDisableReasonInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescAccountDisableReasonUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescAccountDisableReasonId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescAccountDisableReasonId=$systemDescAccountDisableReasonId"
        )
    }
}