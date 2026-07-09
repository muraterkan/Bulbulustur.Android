package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBuyerLastPriceRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleBuyerLastPriceRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerLastPriceRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBuyerLastPriceRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleBuyerLastPriceRequestRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleBuyerLastPriceRequestRepository {

    override suspend fun GetWholesaleBuyerLastPriceRequestListAsync(wholesaleProductId: Int, count: Int): Result<List<WholesaleBuyerLastPriceRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "GetBuyerLastPriceRequestsAsync",
            query = "wholesaleProductId=$wholesaleProductId&count=$count"
        )
    }

    override suspend fun GetWholesaleBuyerLastPriceRequestByIdAsync(wholesaleBuyerLastPriceRequestId: Int): Result<WholesaleBuyerLastPriceRequestUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "GetBuyerLastPriceRequestByIdAsync",
            query = "wholesaleBuyerLastPriceRequestId=$wholesaleBuyerLastPriceRequestId"
        )
    }

    override suspend fun GetWholesaleBuyerLastPriceRequestByIdExtendedAsync(languageId: Int, wholesaleBuyerLastPriceRequestId: Int): Result<WholesaleBuyerLastPriceRequestDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "GetBuyerLastPriceRequestByIdExtendedAsync",
            query = "languageId=$languageId&wholesaleBuyerLastPriceRequestId=$wholesaleBuyerLastPriceRequestId"
        )
    }

    override suspend fun InsertAsync(languageId: Int, model: WholesaleBuyerLastPriceRequestInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "BuyerLastPriceRequestInsertAsync?languageId=$languageId",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: WholesaleBuyerLastPriceRequestUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "BuyerLastPriceRequestUpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(wholesaleBuyerLastPriceRequestId: Int): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "BuyerLastPriceRequestDeleteAsync",
            data = wholesaleBuyerLastPriceRequestId
        )
    }
}
