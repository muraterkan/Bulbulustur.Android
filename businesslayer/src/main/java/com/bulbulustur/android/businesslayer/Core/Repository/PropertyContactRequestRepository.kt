package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyContactRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IPropertyContactRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyContactRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyContactRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class PropertyContactRequestRepository(
    private val apiClient: ApiClient = ApiClient
) : IPropertyContactRequestRepository {

    override suspend fun GetPropertyContactRequestsAsync(
        count: Int
    ): Result<List<PropertyContactRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyContactRequestsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetPropertyContactRequestsByPropertyListingIdAsync(
        propertyListingId: Int,
        count: Int
    ): Result<List<PropertyContactRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyContactRequestsByPropertyListingIdAsync",
            query = "propertyListingId=$propertyListingId&count=$count"
        )
    }

    override suspend fun GetPropertyContactRequestByIdAsync(
        propertyContactRequestId: Int
    ): Result<PropertyContactRequestUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyContactRequestByIdAsync",
            query = "propertyContactRequestId=$propertyContactRequestId"
        )
    }

    override suspend fun GetPropertyContactRequestByIdExtendedAsync(
        propertyContactRequestId: Int
    ): Result<PropertyContactRequestDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyContactRequestByIdExtendedAsync",
            query = "propertyContactRequestId=$propertyContactRequestId"
        )
    }

    override suspend fun InsertAsync(
        model: PropertyContactRequestInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertPropertyContactRequestAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: PropertyContactRequestUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdatePropertyContactRequestAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        propertyContactRequestId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeletePropertyContactRequestAsync",
            query = "propertyContactRequestId=$propertyContactRequestId"
        )
    }
}