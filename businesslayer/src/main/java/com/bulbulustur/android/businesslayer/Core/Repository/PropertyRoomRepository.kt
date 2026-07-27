package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyRoomDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IPropertyRoomRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyRoomInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyRoomUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class PropertyRoomRepository(
    private val apiClient: ApiClient = ApiClient
) : IPropertyRoomRepository {

    override suspend fun GetPropertyRoomsAsync(
        count: Int
    ): Result<List<PropertyRoomDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyRoomsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetPropertyRoomsByPropertyIdAsync(
        propertyId: Int,
        count: Int
    ): Result<List<PropertyRoomDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyRoomsByPropertyIdAsync",
            query = "propertyId=$propertyId&count=$count"
        )
    }

    override suspend fun GetPropertyRoomByIdAsync(
        propertyRoomId: Int
    ): Result<PropertyRoomUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyRoomByIdAsync",
            query = "propertyRoomId=$propertyRoomId"
        )
    }

    override suspend fun GetPropertyRoomByIdExtendedAsync(
        propertyRoomId: Int
    ): Result<PropertyRoomDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyRoomByIdExtendedAsync",
            query = "propertyRoomId=$propertyRoomId"
        )
    }

    override suspend fun InsertAsync(
        model: PropertyRoomInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertPropertyRoomAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: PropertyRoomUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdatePropertyRoomAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        propertyRoomId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeletePropertyRoomAsync",
            query = "propertyRoomId=$propertyRoomId"
        )
    }
}