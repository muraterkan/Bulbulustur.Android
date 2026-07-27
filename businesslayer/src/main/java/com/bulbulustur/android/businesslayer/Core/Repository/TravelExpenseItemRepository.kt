package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelExpenseItemDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelExpenseItemRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelExpenseItemInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelExpenseItemUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelExpenseItemRepository(private val apiClient: ApiClient = ApiClient) : ITravelExpenseItemRepository {

    override suspend fun GetTravelExpenseItemsAsync(count: Int): Result<List<TravelExpenseItemDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelExpenseItemsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelExpenseItemByIdAsync(travelExpenseItemId: Int): Result<TravelExpenseItemUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelExpenseItemByIdAsync",
            query = "travelExpenseItemId=$travelExpenseItemId"
        )
    }

    override suspend fun GetTravelExpenseItemByIdExtendedAsync(travelExpenseItemId: Int): Result<TravelExpenseItemDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelExpenseItemByIdExtendedAsync",
            query = "travelExpenseItemId=$travelExpenseItemId"
        )
    }

    override suspend fun InsertAsync(model: TravelExpenseItemInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelExpenseItemAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelExpenseItemUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelExpenseItemAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelExpenseItemId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelExpenseItemAsync",
            query = "travelExpenseItemId=$travelExpenseItemId"
        )
    }
}