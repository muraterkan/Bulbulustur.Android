package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyListingDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IPropertyListingRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyListingInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyListingUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class PropertyListingRepository(
    private val apiClient: ApiClient = ApiClient
) : IPropertyListingRepository {

    override suspend fun GetPropertyListingsAsync(
        count: Int
    ): Result<List<PropertyListingDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyListingsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetPropertyListingsByMemberIdAsync(
        memberId: Int,
        count: Int
    ): Result<List<PropertyListingDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyListingsByMemberIdAsync",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetPropertyListingByIdAsync(
        propertyListingId: Int
    ): Result<PropertyListingUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyListingByIdAsync",
            query = "propertyListingId=$propertyListingId"
        )
    }

    override suspend fun GetPropertyListingByIdExtendedAsync(
        propertyListingId: Int
    ): Result<PropertyListingDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyListingByIdExtendedAsync",
            query = "propertyListingId=$propertyListingId"
        )
    }

    override suspend fun InsertAsync(
        model: PropertyListingInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertPropertyListingAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: PropertyListingUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdatePropertyListingAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        propertyListingId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeletePropertyListingAsync",
            query = "propertyListingId=$propertyListingId"
        )
    }
}