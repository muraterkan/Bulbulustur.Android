package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.BuyerRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IBuyerRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BuyerRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BuyerRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class BuyerRequestRepository(
    private val apiClient: ApiClient = ApiClient
) : IBuyerRequestRepository {

    override suspend fun GetBuyerRequestListAsync(): Result<List<BuyerRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBuyerRequestListAsync"
        )
    }

    override suspend fun GetBuyerRequestByIdAsync(
        buyerRequestId: Int
    ): Result<BuyerRequestUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBuyerRequestByIdAsync",
            query = "buyerRequestId=$buyerRequestId"
        )
    }

    override suspend fun GetBuyerRequestByIdExtendedAsync(
        buyerRequestId: Int
    ): Result<BuyerRequestDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBuyerRequestByIdExtendedAsync",
            query = "buyerRequestId=$buyerRequestId"
        )
    }

    override suspend fun InsertAsync(
        model: BuyerRequestInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: BuyerRequestUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        buyerRequestId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "buyerRequestId=$buyerRequestId"
        )
    }
}