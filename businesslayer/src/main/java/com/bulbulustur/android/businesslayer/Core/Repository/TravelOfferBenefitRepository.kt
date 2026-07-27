package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelOfferBenefitDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelOfferBenefitRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelOfferBenefitInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelOfferBenefitUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelOfferBenefitRepository(private val apiClient: ApiClient = ApiClient) : ITravelOfferBenefitRepository {

    override suspend fun GetTravelOfferBenefitsAsync(count: Int): Result<List<TravelOfferBenefitDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelOfferBenefitsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelOfferBenefitByIdAsync(travelOfferBenefitId: Int): Result<TravelOfferBenefitUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelOfferBenefitByIdAsync",
            query = "travelOfferBenefitId=$travelOfferBenefitId"
        )
    }

    override suspend fun GetTravelOfferBenefitByIdExtendedAsync(travelOfferBenefitId: Int): Result<TravelOfferBenefitDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelOfferBenefitByIdExtendedAsync",
            query = "travelOfferBenefitId=$travelOfferBenefitId"
        )
    }

    override suspend fun InsertAsync(model: TravelOfferBenefitInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelOfferBenefitAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelOfferBenefitUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelOfferBenefitAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelOfferBenefitId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelOfferBenefitAsync",
            query = "travelOfferBenefitId=$travelOfferBenefitId"
        )
    }
}