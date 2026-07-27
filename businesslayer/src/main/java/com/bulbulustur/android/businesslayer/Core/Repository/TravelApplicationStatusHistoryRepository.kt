package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelApplicationStatusHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelApplicationStatusHistoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelApplicationStatusHistoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelApplicationStatusHistoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelApplicationStatusHistoryRepository(private val apiClient: ApiClient = ApiClient) : ITravelApplicationStatusHistoryRepository {

    override suspend fun GetTravelApplicationStatusHistoriesAsync(count: Int): Result<List<TravelApplicationStatusHistoryDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelApplicationStatusHistoriesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelApplicationStatusHistoryByIdAsync(travelApplicationStatusHistoryId: Int): Result<TravelApplicationStatusHistoryUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelApplicationStatusHistoryByIdAsync",
            query = "travelApplicationStatusHistoryId=$travelApplicationStatusHistoryId"
        )
    }

    override suspend fun GetTravelApplicationStatusHistoryByIdExtendedAsync(travelApplicationStatusHistoryId: Int): Result<TravelApplicationStatusHistoryDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelApplicationStatusHistoryByIdExtendedAsync",
            query = "travelApplicationStatusHistoryId=$travelApplicationStatusHistoryId"
        )
    }

    override suspend fun InsertAsync(model: TravelApplicationStatusHistoryInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelApplicationStatusHistoryAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelApplicationStatusHistoryUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelApplicationStatusHistoryAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelApplicationStatusHistoryId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelApplicationStatusHistoryAsync",
            query = "travelApplicationStatusHistoryId=$travelApplicationStatusHistoryId"
        )
    }
}