package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelOfferDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelOfferRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelOfferInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelOfferUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelOfferRepository(private val apiClient: ApiClient = ApiClient) : ITravelOfferRepository {

    override suspend fun GetTravelOffersAsync(count: Int): Result<List<TravelOfferDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelOffersAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelOfferByIdAsync(travelOfferId: Int): Result<TravelOfferUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelOfferByIdAsync",
            query = "travelOfferId=$travelOfferId"
        )
    }

    override suspend fun GetTravelOfferByIdExtendedAsync(travelOfferId: Int): Result<TravelOfferDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelOfferByIdExtendedAsync",
            query = "travelOfferId=$travelOfferId"
        )
    }

    override suspend fun InsertAsync(model: TravelOfferInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelOfferAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelOfferUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelOfferAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelOfferId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelOfferAsync",
            query = "travelOfferId=$travelOfferId"
        )
    }
}