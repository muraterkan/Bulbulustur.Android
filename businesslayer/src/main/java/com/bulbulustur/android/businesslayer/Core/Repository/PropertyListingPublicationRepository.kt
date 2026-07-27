package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyListingPublicationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IPropertyListingPublicationRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyListingPublicationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyListingPublicationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class PropertyListingPublicationRepository(
    private val apiClient: ApiClient = ApiClient
) : IPropertyListingPublicationRepository {

    override suspend fun GetPropertyListingPublicationsAsync(
        count: Int
    ): Result<List<PropertyListingPublicationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyListingPublicationsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetPropertyListingPublicationsByPropertyListingIdAsync(
        propertyListingId: Int,
        count: Int
    ): Result<List<PropertyListingPublicationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyListingPublicationsByPropertyListingIdAsync",
            query = "propertyListingId=$propertyListingId&count=$count"
        )
    }

    override suspend fun GetPropertyListingPublicationByIdAsync(
        propertyListingPublicationId: Int
    ): Result<PropertyListingPublicationUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyListingPublicationByIdAsync",
            query = "propertyListingPublicationId=$propertyListingPublicationId"
        )
    }

    override suspend fun GetPropertyListingPublicationByIdExtendedAsync(
        propertyListingPublicationId: Int
    ): Result<PropertyListingPublicationDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyListingPublicationByIdExtendedAsync",
            query = "propertyListingPublicationId=$propertyListingPublicationId"
        )
    }

    override suspend fun InsertAsync(
        model: PropertyListingPublicationInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertPropertyListingPublicationAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: PropertyListingPublicationUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdatePropertyListingPublicationAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        propertyListingPublicationId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeletePropertyListingPublicationAsync",
            query = "propertyListingPublicationId=$propertyListingPublicationId"
        )
    }
}