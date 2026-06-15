package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.RefundRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IRefundRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RefundRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RefundRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class RefundRequestRepository(
    private val apiClient: ApiClient = ApiClient
) : IRefundRequestRepository {

    override suspend fun GetRefundRequestListAsync(): Result<List<RefundRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetRefundRequestListAsync"
        )
    }

    override suspend fun GetRefundRequestByIdAsync(
        refundRequestId: Int
    ): Result<RefundRequestUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetRefundRequestByIdAsync",
            query = "refundRequestId=$refundRequestId"
        )
    }

    override suspend fun GetRefundRequestByIdExtendedAsync(
        refundRequestId: Int
    ): Result<RefundRequestDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetRefundRequestByIdExtendedAsync",
            query = "refundRequestId=$refundRequestId"
        )
    }

    override suspend fun InsertAsync(
        model: RefundRequestInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: RefundRequestUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        refundRequestId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "refundRequestId=$refundRequestId"
        )
    }
}