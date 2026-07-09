package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBuyerSampleRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleBuyerSampleRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerSampleRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBuyerSampleRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleBuyerSampleRequestRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleBuyerSampleRequestRepository {

    override suspend fun GetWholesaleBuyerSampleRequestListAsync(wholesaleProductId: Int, count: Int): Result<List<WholesaleBuyerSampleRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "GetBuyerSampleRequestsAsync",
            query = "wholesaleProductId=$wholesaleProductId&count=$count"
        )
    }

    override suspend fun GetWholesaleBuyerSampleRequestByIdExtendedAsync(wholesaleBuyerSampleRequestId: Int): Result<WholesaleBuyerSampleRequestDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "GetBuyerSampleRequestByIdExtendedAsync",
            query = "wholesaleBuyerSampleRequestId=$wholesaleBuyerSampleRequestId"
        )
    }

    override suspend fun InsertAsync(languageId: Int, model: WholesaleBuyerSampleRequestInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "BuyerSampleRequestInsertAsync?languageId=$languageId",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: WholesaleBuyerSampleRequestUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "BuyerSampleRequestUpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(wholesaleBuyerSampleRequestId: Int): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "BuyerSampleRequestDeleteAsync",
            data = wholesaleBuyerSampleRequestId
        )
    }
}
