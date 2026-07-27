package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyListingBoosterDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IPropertyListingBoosterRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyListingBoosterInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyListingBoosterUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class PropertyListingBoosterRepository(
    private val apiClient: ApiClient = ApiClient
) : IPropertyListingBoosterRepository {

    override suspend fun GetPropertyListingBoostersAsync(
        count: Int
    ): Result<List<PropertyListingBoosterDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyListingBoostersAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetPropertyListingBoostersByPropertyListingIdAsync(
        propertyListingId: Int,
        count: Int
    ): Result<List<PropertyListingBoosterDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyListingBoostersByPropertyListingIdAsync",
            query = "propertyListingId=$propertyListingId&count=$count"
        )
    }

    override suspend fun GetPropertyListingBoosterByIdAsync(
        propertyListingBoosterId: Int
    ): Result<PropertyListingBoosterUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyListingBoosterByIdAsync",
            query = "propertyListingBoosterId=$propertyListingBoosterId"
        )
    }

    override suspend fun GetPropertyListingBoosterByIdExtendedAsync(
        propertyListingBoosterId: Int
    ): Result<PropertyListingBoosterDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyListingBoosterByIdExtendedAsync",
            query = "propertyListingBoosterId=$propertyListingBoosterId"
        )
    }

    override suspend fun InsertAsync(
        model: PropertyListingBoosterInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertPropertyListingBoosterAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: PropertyListingBoosterUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdatePropertyListingBoosterAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        propertyListingBoosterId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeletePropertyListingBoosterAsync",
            query = "propertyListingBoosterId=$propertyListingBoosterId"
        )
    }
}