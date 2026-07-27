package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IPropertyRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class PropertyRepository(
    private val apiClient: ApiClient = ApiClient
) : IPropertyRepository {

    override suspend fun GetPropertiesAsync(
        count: Int
    ): Result<List<PropertyDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertiesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetPropertiesByMemberIdAsync(
        memberId: Int,
        count: Int
    ): Result<List<PropertyDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertiesByMemberIdAsync",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetPropertyByIdAsync(
        propertyId: Int
    ): Result<PropertyUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyByIdAsync",
            query = "propertyId=$propertyId"
        )
    }

    override suspend fun GetPropertyByIdExtendedAsync(
        propertyId: Int
    ): Result<PropertyDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyByIdExtendedAsync",
            query = "propertyId=$propertyId"
        )
    }

    override suspend fun InsertAsync(
        model: PropertyInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertPropertyAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: PropertyUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdatePropertyAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        propertyId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeletePropertyAsync",
            query = "propertyId=$propertyId"
        )
    }
}