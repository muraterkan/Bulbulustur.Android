package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductSizeTypeByCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductSizeTypeByCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductSizeTypeByCategoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductSizeTypeByCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductSizeTypeByCategoryRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductSizeTypeByCategoryRepository {

    override suspend fun GetProductSizeTypeByCategoryListAsync(): Result<List<ProductSizeTypeByCategoryDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductSizeTypeByCategoryListAsync"
        )
    }

    override suspend fun GetProductSizeTypeByCategoryByIdAsync(
        productSizeTypeByCategoryId: Int
    ): Result<ProductSizeTypeByCategoryUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductSizeTypeByCategoryByIdAsync",
            query = "productSizeTypeByCategoryId=$productSizeTypeByCategoryId"
        )
    }

    override suspend fun GetProductSizeTypeByCategoryByIdExtendedAsync(
        productSizeTypeByCategoryId: Int
    ): Result<ProductSizeTypeByCategoryDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductSizeTypeByCategoryByIdExtendedAsync",
            query = "productSizeTypeByCategoryId=$productSizeTypeByCategoryId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductSizeTypeByCategoryInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductSizeTypeByCategoryUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productSizeTypeByCategoryId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productSizeTypeByCategoryId=$productSizeTypeByCategoryId"
        )
    }
}