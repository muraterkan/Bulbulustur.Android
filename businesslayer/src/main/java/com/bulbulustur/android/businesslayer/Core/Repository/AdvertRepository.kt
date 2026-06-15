package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AdvertDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAdvertRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AdvertInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AdvertUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AdvertRepository(
    private val apiClient: ApiClient = ApiClient
) : IAdvertRepository {

    override suspend fun GetAdvertListAsync(): Result<List<AdvertDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAdvertListAsync"
        )
    }

    override suspend fun GetAdvertByIdAsync(
        advertId: Int
    ): Result<AdvertUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAdvertByIdAsync",
            query = "advertId=$advertId"
        )
    }

    override suspend fun GetAdvertByIdExtendedAsync(
        advertId: Int
    ): Result<AdvertDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAdvertByIdExtendedAsync",
            query = "advertId=$advertId"
        )
    }

    override suspend fun InsertAsync(
        model: AdvertInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: AdvertUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        advertId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "advertId=$advertId"
        )
    }
}