package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelOfferStatusHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelOfferStatusHistoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelOfferStatusHistoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelOfferStatusHistoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelOfferStatusHistoryRepository(private val apiClient: ApiClient = ApiClient) : ITravelOfferStatusHistoryRepository {

    override suspend fun GetTravelOfferStatusHistoriesAsync(count: Int): Result<List<TravelOfferStatusHistoryDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelOfferStatusHistoriesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelOfferStatusHistoryByIdAsync(travelOfferStatusHistoryId: Int): Result<TravelOfferStatusHistoryUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelOfferStatusHistoryByIdAsync",
            query = "travelOfferStatusHistoryId=$travelOfferStatusHistoryId"
        )
    }

    override suspend fun GetTravelOfferStatusHistoryByIdExtendedAsync(travelOfferStatusHistoryId: Int): Result<TravelOfferStatusHistoryDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelOfferStatusHistoryByIdExtendedAsync",
            query = "travelOfferStatusHistoryId=$travelOfferStatusHistoryId"
        )
    }

    override suspend fun InsertAsync(model: TravelOfferStatusHistoryInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelOfferStatusHistoryAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelOfferStatusHistoryUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelOfferStatusHistoryAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelOfferStatusHistoryId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelOfferStatusHistoryAsync",
            query = "travelOfferStatusHistoryId=$travelOfferStatusHistoryId"
        )
    }
}