package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductFavoriteRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductFavoriteInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductFavoriteUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductFavoriteRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductFavoriteRepository {

    override suspend fun GetProductFavoriteListAsync(): Result<List<ProductFavoriteDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductFavoriteListAsync"
        )
    }

    override suspend fun GetProductFavoriteByIdAsync(
        productFavoriteId: Int
    ): Result<ProductFavoriteUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductFavoriteByIdAsync",
            query = "productFavoriteId=$productFavoriteId"
        )
    }

    override suspend fun GetProductFavoriteByIdExtendedAsync(
        productFavoriteId: Int
    ): Result<ProductFavoriteDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductFavoriteByIdExtendedAsync",
            query = "productFavoriteId=$productFavoriteId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductFavoriteInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductFavoriteUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productFavoriteId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productFavoriteId=$productFavoriteId"
        )
    }
}