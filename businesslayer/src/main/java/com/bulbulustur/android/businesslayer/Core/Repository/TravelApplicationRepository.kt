package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelApplicationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelApplicationRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelApplicationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelApplicationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelApplicationRepository(private val apiClient: ApiClient = ApiClient) : ITravelApplicationRepository {

    override suspend fun GetTravelApplicationsAsync(count: Int): Result<List<TravelApplicationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelApplicationsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelApplicationByIdAsync(travelApplicationId: Int): Result<TravelApplicationUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelApplicationByIdAsync",
            query = "travelApplicationId=$travelApplicationId"
        )
    }

    override suspend fun GetTravelApplicationByIdExtendedAsync(travelApplicationId: Int): Result<TravelApplicationDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelApplicationByIdExtendedAsync",
            query = "travelApplicationId=$travelApplicationId"
        )
    }

    override suspend fun InsertAsync(model: TravelApplicationInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelApplicationAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelApplicationUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelApplicationAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelApplicationId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelApplicationAsync",
            query = "travelApplicationId=$travelApplicationId"
        )
    }
}