package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductRepository {

    override suspend fun GetProductListAsync(): Result<List<ProductDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductListAsync"
        )
    }

    override suspend fun GetProductByIdAsync(
        productId: Int
    ): Result<ProductUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductByIdAsync",
            query = "productId=$productId"
        )
    }

    override suspend fun GetProductByIdExtendedAsync(
        productId: Int
    ): Result<ProductDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductByIdExtendedAsync",
            query = "productId=$productId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productId=$productId"
        )
    }
}