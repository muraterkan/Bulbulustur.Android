package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TravelPhotoDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITravelPhotoRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelPhotoInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelPhotoUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TravelPhotoRepository(private val apiClient: ApiClient = ApiClient) : ITravelPhotoRepository {

    override suspend fun GetTravelPhotosAsync(count: Int): Result<List<TravelPhotoDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelPhotosAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetTravelPhotoByIdAsync(travelPhotoId: Int): Result<TravelPhotoUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelPhotoByIdAsync",
            query = "travelPhotoId=$travelPhotoId"
        )
    }

    override suspend fun GetTravelPhotoByIdExtendedAsync(travelPhotoId: Int): Result<TravelPhotoDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "GetTravelPhotoByIdExtendedAsync",
            query = "travelPhotoId=$travelPhotoId"
        )
    }

    override suspend fun InsertAsync(model: TravelPhotoInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "InsertTravelPhotoAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: TravelPhotoUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "UpdateTravelPhotoAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(travelPhotoId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.TRAVEL_GIRLS_BASE_URL,
            method = "DeleteTravelPhotoAsync",
            query = "travelPhotoId=$travelPhotoId"
        )
    }
}