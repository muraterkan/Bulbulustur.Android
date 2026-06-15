package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductStoreBasedFeatureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductStoreBasedFeatureRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductStoreBasedFeatureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductStoreBasedFeatureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductStoreBasedFeatureRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductStoreBasedFeatureRepository {

    override suspend fun GetProductStoreBasedFeatureListAsync(): Result<List<ProductStoreBasedFeatureDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductStoreBasedFeatureListAsync"
        )
    }

    override suspend fun GetProductStoreBasedFeatureByIdAsync(
        productStoreBasedFeatureId: Int
    ): Result<ProductStoreBasedFeatureUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductStoreBasedFeatureByIdAsync",
            query = "productStoreBasedFeatureId=$productStoreBasedFeatureId"
        )
    }

    override suspend fun GetProductStoreBasedFeatureByIdExtendedAsync(
        productStoreBasedFeatureId: Int
    ): Result<ProductStoreBasedFeatureDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductStoreBasedFeatureByIdExtendedAsync",
            query = "productStoreBasedFeatureId=$productStoreBasedFeatureId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductStoreBasedFeatureInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductStoreBasedFeatureUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productStoreBasedFeatureId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productStoreBasedFeatureId=$productStoreBasedFeatureId"
        )
    }
}