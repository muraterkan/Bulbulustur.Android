package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IReturnRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ReturnRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReturnRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ReturnRequestRepository(
    private val apiClient: ApiClient = ApiClient
) : IReturnRequestRepository {

    override suspend fun GetReturnRequestListAsync(): Result<List<ReturnRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetReturnRequestListAsync"
        )
    }

    override suspend fun GetReturnRequestByIdAsync(
        returnRequestId: Int
    ): Result<ReturnRequestUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetReturnRequestByIdAsync",
            query = "returnRequestId=$returnRequestId"
        )
    }

    override suspend fun GetReturnRequestByIdExtendedAsync(
        returnRequestId: Int
    ): Result<ReturnRequestDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetReturnRequestByIdExtendedAsync",
            query = "returnRequestId=$returnRequestId"
        )
    }

    override suspend fun InsertAsync(
        model: ReturnRequestInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ReturnRequestUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        returnRequestId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "returnRequestId=$returnRequestId"
        )
    }
}