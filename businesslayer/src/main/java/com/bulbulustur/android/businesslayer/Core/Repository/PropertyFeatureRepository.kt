package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyFeatureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IPropertyFeatureRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyFeatureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyFeatureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class PropertyFeatureRepository(
    private val apiClient: ApiClient = ApiClient
) : IPropertyFeatureRepository {

    override suspend fun GetPropertyFeaturesAsync(
        count: Int
    ): Result<List<PropertyFeatureDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyFeaturesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetPropertyFeaturesByPropertyIdAsync(
        propertyId: Int,
        count: Int
    ): Result<List<PropertyFeatureDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyFeaturesByPropertyIdAsync",
            query = "propertyId=$propertyId&count=$count"
        )
    }

    override suspend fun GetPropertyFeatureByIdAsync(
        propertyFeatureId: Int
    ): Result<PropertyFeatureUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyFeatureByIdAsync",
            query = "propertyFeatureId=$propertyFeatureId"
        )
    }

    override suspend fun GetPropertyFeatureByIdExtendedAsync(
        propertyFeatureId: Int
    ): Result<PropertyFeatureDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyFeatureByIdExtendedAsync",
            query = "propertyFeatureId=$propertyFeatureId"
        )
    }

    override suspend fun InsertAsync(
        model: PropertyFeatureInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertPropertyFeatureAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: PropertyFeatureUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdatePropertyFeatureAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        propertyFeatureId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeletePropertyFeatureAsync",
            query = "propertyFeatureId=$propertyFeatureId"
        )
    }
}