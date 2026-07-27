package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelStyleDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelStyleRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelStyleInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelStyleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelStyleRepository(private val apiClient: ApiClient = ApiClient) : ITravelStyleRepository {

    override suspend fun GetTravelStylesAsync(count: Int): Result<List<TravelStyleDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelStylesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelStyleByIdAsync(travelStyleId: Int): Result<TravelStyleUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelStyleByIdAsync",
            query = "travelStyleId=$travelStyleId"
        )
    }

    override suspend fun GetTravelStyleByIdExtendedAsync(travelStyleId: Int): Result<TravelStyleDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelStyleByIdExtendedAsync",
            query = "travelStyleId=$travelStyleId"
        )
    }

    override suspend fun InsertAsync(model: TravelStyleInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelStyleAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelStyleUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelStyleAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelStyleId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelStyleAsync",
            query = "travelStyleId=$travelStyleId"
        )
    }
}