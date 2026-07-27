package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelApplicationParticipantDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelApplicationParticipantRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelApplicationParticipantInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelApplicationParticipantUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelApplicationParticipantRepository(private val apiClient: ApiClient = ApiClient) : ITravelApplicationParticipantRepository {

    override suspend fun GetTravelApplicationParticipantsAsync(count: Int): Result<List<TravelApplicationParticipantDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelApplicationParticipantsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelApplicationParticipantByIdAsync(travelApplicationParticipantId: Int): Result<TravelApplicationParticipantUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelApplicationParticipantByIdAsync",
            query = "travelApplicationParticipantId=$travelApplicationParticipantId"
        )
    }

    override suspend fun GetTravelApplicationParticipantByIdExtendedAsync(travelApplicationParticipantId: Int): Result<TravelApplicationParticipantDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelApplicationParticipantByIdExtendedAsync",
            query = "travelApplicationParticipantId=$travelApplicationParticipantId"
        )
    }

    override suspend fun InsertAsync(model: TravelApplicationParticipantInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelApplicationParticipantAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelApplicationParticipantUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelApplicationParticipantAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelApplicationParticipantId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelApplicationParticipantAsync",
            query = "travelApplicationParticipantId=$travelApplicationParticipantId"
        )
    }
}