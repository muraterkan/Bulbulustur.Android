package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBuyerCustomizeRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleBuyerCustomizeRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerCustomizeRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBuyerCustomizeRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleBuyerCustomizeRequestRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleBuyerCustomizeRequestRepository {

    override suspend fun GetWholesaleBuyerCustomizeRequestListAsync(wholesaleProductId: Int, count: Int): Result<List<WholesaleBuyerCustomizeRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "GetBuyerCustomizeRequestsAsync",
            query = "wholesaleProductId=$wholesaleProductId&count=$count"
        )
    }

    override suspend fun GetWholesaleBuyerCustomizeRequestByIdAsync(wholesaleBuyerCustomizeRequestId: Int): Result<WholesaleBuyerCustomizeRequestUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "GetBuyerCustomizeRequestByIdAsync",
            query = "wholesaleBuyerCustomizeRequestId=$wholesaleBuyerCustomizeRequestId"
        )
    }

    override suspend fun GetWholesaleBuyerCustomizeRequestByIdExtendedAsync(languageId: Int, wholesaleBuyerCustomizeRequestId: Int): Result<WholesaleBuyerCustomizeRequestDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "GetBuyerCustomizeRequestByIdExtendedAsync",
            query = "languageId=$languageId&wholesaleBuyerCustomizeRequestId=$wholesaleBuyerCustomizeRequestId"
        )
    }

    override suspend fun InsertAsync(languageId: Int, model: WholesaleBuyerCustomizeRequestInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "BuyerCustomizeRequestInsertAsync?languageId=$languageId",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: WholesaleBuyerCustomizeRequestUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "BuyerCustomizeRequestUpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(wholesaleBuyerCustomizeRequestId: Int): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "BuyerCustomizeRequestDeleteAsync",
            data = wholesaleBuyerCustomizeRequestId
        )
    }
}
