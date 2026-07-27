package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelAcceptedApplicantTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelAcceptedApplicantTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelAcceptedApplicantTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelAcceptedApplicantTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelAcceptedApplicantTypeRepository(private val apiClient: ApiClient = ApiClient) : ITravelAcceptedApplicantTypeRepository {

    override suspend fun GetTravelAcceptedApplicantTypesAsync(count: Int): Result<List<TravelAcceptedApplicantTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelAcceptedApplicantTypesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelAcceptedApplicantTypeByIdAsync(travelAcceptedApplicantTypeId: Int): Result<TravelAcceptedApplicantTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelAcceptedApplicantTypeByIdAsync",
            query = "travelAcceptedApplicantTypeId=$travelAcceptedApplicantTypeId"
        )
    }

    override suspend fun GetTravelAcceptedApplicantTypeByIdExtendedAsync(travelAcceptedApplicantTypeId: Int): Result<TravelAcceptedApplicantTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelAcceptedApplicantTypeByIdExtendedAsync",
            query = "travelAcceptedApplicantTypeId=$travelAcceptedApplicantTypeId"
        )
    }

    override suspend fun InsertAsync(model: TravelAcceptedApplicantTypeInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelAcceptedApplicantTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelAcceptedApplicantTypeUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelAcceptedApplicantTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelAcceptedApplicantTypeId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelAcceptedApplicantTypeAsync",
            query = "travelAcceptedApplicantTypeId=$travelAcceptedApplicantTypeId"
        )
    }
}