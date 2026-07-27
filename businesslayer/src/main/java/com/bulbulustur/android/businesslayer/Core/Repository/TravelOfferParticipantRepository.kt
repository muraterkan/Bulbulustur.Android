package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelOfferParticipantDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelOfferParticipantRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelOfferParticipantInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelOfferParticipantUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelOfferParticipantRepository(private val apiClient: ApiClient = ApiClient) : ITravelOfferParticipantRepository {

    override suspend fun GetTravelOfferParticipantsAsync(count: Int): Result<List<TravelOfferParticipantDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelOfferParticipantsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelOfferParticipantByIdAsync(travelOfferParticipantId: Int): Result<TravelOfferParticipantUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelOfferParticipantByIdAsync",
            query = "travelOfferParticipantId=$travelOfferParticipantId"
        )
    }

    override suspend fun GetTravelOfferParticipantByIdExtendedAsync(travelOfferParticipantId: Int): Result<TravelOfferParticipantDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelOfferParticipantByIdExtendedAsync",
            query = "travelOfferParticipantId=$travelOfferParticipantId"
        )
    }

    override suspend fun InsertAsync(model: TravelOfferParticipantInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelOfferParticipantAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelOfferParticipantUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelOfferParticipantAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelOfferParticipantId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelOfferParticipantAsync",
            query = "travelOfferParticipantId=$travelOfferParticipantId"
        )
    }
}