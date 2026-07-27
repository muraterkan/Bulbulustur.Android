package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelBoosterDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelBoosterRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelBoosterInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelBoosterUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelBoosterRepository(private val apiClient: ApiClient = ApiClient) : ITravelBoosterRepository {

    override suspend fun GetTravelBoostersAsync(count: Int): Result<List<TravelBoosterDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelBoostersAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelBoosterByIdAsync(travelBoosterId: Int): Result<TravelBoosterUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelBoosterByIdAsync",
            query = "travelBoosterId=$travelBoosterId"
        )
    }

    override suspend fun GetTravelBoosterByIdExtendedAsync(travelBoosterId: Int): Result<TravelBoosterDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelBoosterByIdExtendedAsync",
            query = "travelBoosterId=$travelBoosterId"
        )
    }

    override suspend fun InsertAsync(model: TravelBoosterInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelBoosterAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelBoosterUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelBoosterAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelBoosterId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelBoosterAsync",
            query = "travelBoosterId=$travelBoosterId"
        )
    }
}