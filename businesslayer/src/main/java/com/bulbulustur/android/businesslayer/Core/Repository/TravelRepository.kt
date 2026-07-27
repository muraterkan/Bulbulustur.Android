package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelRepository(private val apiClient: ApiClient = ApiClient) : ITravelRepository {

    override suspend fun GetTravelsAsync(count: Int): Result<List<TravelDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelByIdAsync(travelId: Int): Result<TravelUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelByIdAsync",
            query = "travelId=$travelId"
        )
    }

    override suspend fun GetTravelByIdExtendedAsync(travelId: Int): Result<TravelDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelByIdExtendedAsync",
            query = "travelId=$travelId"
        )
    }

    override suspend fun InsertAsync(model: TravelInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelAsync",
            query = "travelId=$travelId"
        )
    }
}