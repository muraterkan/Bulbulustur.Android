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

    override suspend fun GetBuyerRequestsAsync(count: Int): Result<List<BuyerRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "BuyerRequest/GetBuyerRequestsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetBuyerRequestsByMemberAsync(memberId: Int, count: Int): Result<List<BuyerRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "BuyerRequest/GetBuyerRequestsByMemberAsync",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetBuyerRequestsByIdAsync(buyerRequestKey: String): Result<BuyerRequestUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "BuyerRequest/GetBuyerRequestsByIdAsync",
            query = "buyerRequestKey=$buyerRequestKey"
        )
    }

    override suspend fun GetBuyerRequestsByIdExtendedAsync(buyerRequestKey: String): Result<BuyerRequestDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "BuyerRequest/GetBuyerRequestsByIdExtendedAsync",
            query = "buyerRequestKey=$buyerRequestKey"
        )
    }

    override suspend fun InsertAsync(model: BuyerRequestInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "BuyerRequest/BuyerRequestInsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: BuyerRequestUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "BuyerRequest/BuyerRequestUpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(buyerRequestKey: String): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "BuyerRequest/BuyerRequestDeleteAsync",
            data = buyerRequestKey
        )
    }

    override suspend fun GetPastRequestsAsync(count: Int): Result<List<BuyerRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "BuyerRequest/GetPastRequestsAsync",
            query = "count=$count"
        )
    }
}