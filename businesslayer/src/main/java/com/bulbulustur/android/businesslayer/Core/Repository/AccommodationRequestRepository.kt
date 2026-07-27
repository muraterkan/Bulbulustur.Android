package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AccommodationRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAccommodationRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AccommodationRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AccommodationRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AccommodationRequestRepository(
    private val apiClient: ApiClient = ApiClient
) : IAccommodationRequestRepository {

    override suspend fun GetAccommodationRequestsAsync(
        count: Int
    ): Result<List<AccommodationRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetAccommodationRequestsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetAccommodationRequestsByMemberIdAsync(
        memberId: Int,
        count: Int
    ): Result<List<AccommodationRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetAccommodationRequestsByMemberIdAsync",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetAccommodationRequestByIdAsync(
        accommodationRequestId: Int
    ): Result<AccommodationRequestUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetAccommodationRequestByIdAsync",
            query = "accommodationRequestId=$accommodationRequestId"
        )
    }

    override suspend fun GetAccommodationRequestByIdExtendedAsync(
        accommodationRequestId: Int
    ): Result<AccommodationRequestDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetAccommodationRequestByIdExtendedAsync",
            query = "accommodationRequestId=$accommodationRequestId"
        )
    }

    override suspend fun InsertAsync(
        model: AccommodationRequestInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertAccommodationRequestAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: AccommodationRequestUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdateAccommodationRequestAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        accommodationRequestId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeleteAccommodationRequestAsync",
            query = "accommodationRequestId=$accommodationRequestId"
        )
    }
}