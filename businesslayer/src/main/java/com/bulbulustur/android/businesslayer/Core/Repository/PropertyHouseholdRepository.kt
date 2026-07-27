package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyHouseholdDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IPropertyHouseholdRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyHouseholdInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyHouseholdUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class PropertyHouseholdRepository(
    private val apiClient: ApiClient = ApiClient
) : IPropertyHouseholdRepository {

    override suspend fun GetPropertyHouseholdsAsync(
        count: Int
    ): Result<List<PropertyHouseholdDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyHouseholdsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetPropertyHouseholdsByPropertyIdAsync(
        propertyId: Int,
        count: Int
    ): Result<List<PropertyHouseholdDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyHouseholdsByPropertyIdAsync",
            query = "propertyId=$propertyId&count=$count"
        )
    }

    override suspend fun GetPropertyHouseholdByIdAsync(
        propertyHouseholdId: Int
    ): Result<PropertyHouseholdUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyHouseholdByIdAsync",
            query = "propertyHouseholdId=$propertyHouseholdId"
        )
    }

    override suspend fun GetPropertyHouseholdByIdExtendedAsync(
        propertyHouseholdId: Int
    ): Result<PropertyHouseholdDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyHouseholdByIdExtendedAsync",
            query = "propertyHouseholdId=$propertyHouseholdId"
        )
    }

    override suspend fun InsertAsync(
        model: PropertyHouseholdInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertPropertyHouseholdAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: PropertyHouseholdUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdatePropertyHouseholdAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        propertyHouseholdId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeletePropertyHouseholdAsync",
            query = "propertyHouseholdId=$propertyHouseholdId"
        )
    }
}