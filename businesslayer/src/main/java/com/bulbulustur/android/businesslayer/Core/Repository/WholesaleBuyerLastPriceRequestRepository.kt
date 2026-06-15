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

    override suspend fun GetWholesaleBuyerLastPriceRequestListAsync():
            Result<List<WholesaleBuyerLastPriceRequestDTO>> {

        return apiClient.GetAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "GetWholesaleBuyerLastPriceRequestListAsync"
        )
    }

    override suspend fun GetWholesaleBuyerLastPriceRequestByIdAsync(
        wholesaleBuyerLastPriceRequestId: Int
    ): Result<WholesaleBuyerLastPriceRequestUpdateModel?> {

        return apiClient.GetAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "GetWholesaleBuyerLastPriceRequestByIdAsync",
            query = "wholesaleBuyerLastPriceRequestId=$wholesaleBuyerLastPriceRequestId"
        )
    }

    override suspend fun GetWholesaleBuyerLastPriceRequestByIdExtendedAsync(
        wholesaleBuyerLastPriceRequestId: Int
    ): Result<WholesaleBuyerLastPriceRequestDTO?> {

        return apiClient.GetAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "GetWholesaleBuyerLastPriceRequestByIdExtendedAsync",
            query = "wholesaleBuyerLastPriceRequestId=$wholesaleBuyerLastPriceRequestId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleBuyerLastPriceRequestInsertModel
    ): Result<Unit> {

        return apiClient.PostAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleBuyerLastPriceRequestUpdateModel
    ): Result<Unit> {

        return apiClient.PostAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleBuyerLastPriceRequestId: Int
    ): Result<Unit> {

        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.BUYER_REQUEST_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleBuyerLastPriceRequestId=$wholesaleBuyerLastPriceRequestId"
        )
    }
}