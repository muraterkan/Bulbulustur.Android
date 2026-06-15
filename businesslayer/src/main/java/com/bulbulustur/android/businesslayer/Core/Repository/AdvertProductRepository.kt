package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AdvertProductDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAdvertProductRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AdvertProductInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AdvertProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AdvertProductRepository(
    private val apiClient: ApiClient = ApiClient
) : IAdvertProductRepository {

    override suspend fun GetAdvertProductListAsync(): Result<List<AdvertProductDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAdvertProductListAsync"
        )
    }

    override suspend fun GetAdvertProductByIdAsync(
        advertProductId: Int
    ): Result<AdvertProductUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAdvertProductByIdAsync",
            query = "advertProductId=$advertProductId"
        )
    }

    override suspend fun GetAdvertProductByIdExtendedAsync(
        advertProductId: Int
    ): Result<AdvertProductDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAdvertProductByIdExtendedAsync",
            query = "advertProductId=$advertProductId"
        )
    }

    override suspend fun InsertAsync(
        model: AdvertProductInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: AdvertProductUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        advertProductId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "advertProductId=$advertProductId"
        )
    }
}