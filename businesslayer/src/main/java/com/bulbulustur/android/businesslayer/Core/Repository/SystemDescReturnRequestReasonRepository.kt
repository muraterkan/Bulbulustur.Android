package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReturnRequestReasonDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescReturnRequestReasonRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescReturnRequestReasonInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescReturnRequestReasonUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescReturnRequestReasonRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescReturnRequestReasonRepository {

    override suspend fun GetSystemDescReturnRequestReasonsAsync(
        count: Int
    ): Result<List<SystemDescReturnRequestReasonDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescReturnRequestReasonsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetSystemDescReturnRequestReasonByIdAsync(
        systemDescReturnRequestReasonId: Int
    ): Result<SystemDescReturnRequestReasonUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescReturnRequestReasonByIdAsync",
            query = "systemDescReturnRequestReasonId=$systemDescReturnRequestReasonId"
        )
    }

    override suspend fun GetSystemDescReturnRequestReasonByIdExtendedAsync(
        systemDescReturnRequestReasonId: Int
    ): Result<SystemDescReturnRequestReasonDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescReturnRequestReasonByIdExtendedAsync",
            query = "systemDescReturnRequestReasonId=$systemDescReturnRequestReasonId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescReturnRequestReasonInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "InsertSystemDescReturnRequestReasonAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescReturnRequestReasonUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "UpdateSystemDescReturnRequestReasonAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescReturnRequestReasonId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "DeleteSystemDescReturnRequestReasonAsync",
            query = "systemDescReturnRequestReasonId=$systemDescReturnRequestReasonId"
        )
    }
}