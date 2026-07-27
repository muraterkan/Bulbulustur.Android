package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelReservationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelReservationRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelReservationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelReservationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelReservationRepository(private val apiClient: ApiClient = ApiClient) : ITravelReservationRepository {

    override suspend fun GetTravelReservationsAsync(count: Int): Result<List<TravelReservationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelReservationsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelReservationByIdAsync(travelReservationId: Int): Result<TravelReservationUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelReservationByIdAsync",
            query = "travelReservationId=$travelReservationId"
        )
    }

    override suspend fun GetTravelReservationByIdExtendedAsync(travelReservationId: Int): Result<TravelReservationDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelReservationByIdExtendedAsync",
            query = "travelReservationId=$travelReservationId"
        )
    }

    override suspend fun InsertAsync(model: TravelReservationInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelReservationAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelReservationUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelReservationAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelReservationId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelReservationAsync",
            query = "travelReservationId=$travelReservationId"
        )
    }
}