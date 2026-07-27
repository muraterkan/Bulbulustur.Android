package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyPhotoDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IPropertyPhotoRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyPhotoInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyPhotoUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class PropertyPhotoRepository(
    private val apiClient: ApiClient = ApiClient
) : IPropertyPhotoRepository {

    override suspend fun GetPropertyPhotosAsync(
        count: Int
    ): Result<List<PropertyPhotoDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyPhotosAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetPropertyPhotosByPropertyIdAsync(
        propertyId: Int,
        count: Int
    ): Result<List<PropertyPhotoDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyPhotosByPropertyIdAsync",
            query = "propertyId=$propertyId&count=$count"
        )
    }

    override suspend fun GetPropertyPhotoByIdAsync(
        propertyPhotoId: Int
    ): Result<PropertyPhotoUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyPhotoByIdAsync",
            query = "propertyPhotoId=$propertyPhotoId"
        )
    }

    override suspend fun GetPropertyPhotoByIdExtendedAsync(
        propertyPhotoId: Int
    ): Result<PropertyPhotoDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetPropertyPhotoByIdExtendedAsync",
            query = "propertyPhotoId=$propertyPhotoId"
        )
    }

    override suspend fun InsertAsync(
        model: PropertyPhotoInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertPropertyPhotoAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: PropertyPhotoUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdatePropertyPhotoAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        propertyPhotoId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeletePropertyPhotoAsync",
            query = "propertyPhotoId=$propertyPhotoId"
        )
    }
}