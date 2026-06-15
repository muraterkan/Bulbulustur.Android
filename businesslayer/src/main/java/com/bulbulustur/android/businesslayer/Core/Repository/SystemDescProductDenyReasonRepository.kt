package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescProductDenyReasonDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescProductDenyReasonRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescProductDenyReasonInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescProductDenyReasonUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescProductDenyReasonRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescProductDenyReasonRepository {

    override suspend fun GetSystemDescProductDenyReasonListAsync(): Result<List<SystemDescProductDenyReasonDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescProductDenyReasonListAsync"
        )
    }

    override suspend fun GetSystemDescProductDenyReasonByIdAsync(
        systemDescProductDenyReasonId: Int
    ): Result<SystemDescProductDenyReasonUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescProductDenyReasonByIdAsync",
            query = "systemDescProductDenyReasonId=$systemDescProductDenyReasonId"
        )
    }

    override suspend fun GetSystemDescProductDenyReasonByIdExtendedAsync(
        systemDescProductDenyReasonId: Int
    ): Result<SystemDescProductDenyReasonDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescProductDenyReasonByIdExtendedAsync",
            query = "systemDescProductDenyReasonId=$systemDescProductDenyReasonId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescProductDenyReasonInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescProductDenyReasonUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescProductDenyReasonId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescProductDenyReasonId=$systemDescProductDenyReasonId"
        )
    }
}