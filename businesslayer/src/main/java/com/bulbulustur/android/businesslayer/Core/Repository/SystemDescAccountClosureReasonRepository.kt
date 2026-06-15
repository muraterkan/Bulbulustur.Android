package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAccountClosureReasonDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescAccountClosureReasonRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescAccountClosureReasonInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescAccountClosureReasonUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescAccountClosureReasonRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescAccountClosureReasonRepository {

    override suspend fun GetSystemDescAccountClosureReasonListAsync(): Result<List<SystemDescAccountClosureReasonDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescAccountClosureReasonListAsync"
        )
    }

    override suspend fun GetSystemDescAccountClosureReasonByIdAsync(
        systemDescAccountClosureReasonId: Int
    ): Result<SystemDescAccountClosureReasonUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescAccountClosureReasonByIdAsync",
            query = "systemDescAccountClosureReasonId=$systemDescAccountClosureReasonId"
        )
    }

    override suspend fun GetSystemDescAccountClosureReasonByIdExtendedAsync(
        systemDescAccountClosureReasonId: Int
    ): Result<SystemDescAccountClosureReasonDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescAccountClosureReasonByIdExtendedAsync",
            query = "systemDescAccountClosureReasonId=$systemDescAccountClosureReasonId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescAccountClosureReasonInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescAccountClosureReasonUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescAccountClosureReasonId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescAccountClosureReasonId=$systemDescAccountClosureReasonId"
        )
    }
}