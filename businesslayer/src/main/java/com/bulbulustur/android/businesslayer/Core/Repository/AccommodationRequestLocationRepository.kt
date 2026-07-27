package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AccommodationRequestLocationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAccommodationRequestLocationRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AccommodationRequestLocationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AccommodationRequestLocationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AccommodationRequestLocationRepository(
    private val apiClient: ApiClient = ApiClient
) : IAccommodationRequestLocationRepository {

    override suspend fun GetAccommodationRequestLocationsAsync(
        count: Int
    ): Result<List<AccommodationRequestLocationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetAccommodationRequestLocationsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetAccommodationRequestLocationsByAccommodationRequestIdAsync(
        accommodationRequestId: Int,
        count: Int
    ): Result<List<AccommodationRequestLocationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetAccommodationRequestLocationsByAccommodationRequestIdAsync",
            query = "accommodationRequestId=$accommodationRequestId&count=$count"
        )
    }

    override suspend fun GetAccommodationRequestLocationByIdAsync(
        accommodationRequestLocationId: Int
    ): Result<AccommodationRequestLocationUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetAccommodationRequestLocationByIdAsync",
            query = "accommodationRequestLocationId=$accommodationRequestLocationId"
        )
    }

    override suspend fun GetAccommodationRequestLocationByIdExtendedAsync(
        accommodationRequestLocationId: Int
    ): Result<AccommodationRequestLocationDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetAccommodationRequestLocationByIdExtendedAsync",
            query = "accommodationRequestLocationId=$accommodationRequestLocationId"
        )
    }

    override suspend fun InsertAsync(
        model: AccommodationRequestLocationInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertAccommodationRequestLocationAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: AccommodationRequestLocationUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdateAccommodationRequestLocationAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        accommodationRequestLocationId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeleteAccommodationRequestLocationAsync",
            query = "accommodationRequestLocationId=$accommodationRequestLocationId"
        )
    }
}