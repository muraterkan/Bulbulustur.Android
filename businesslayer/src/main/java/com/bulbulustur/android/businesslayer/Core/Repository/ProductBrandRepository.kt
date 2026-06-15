package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBrandRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductBrandInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrandUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductBrandRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductBrandRepository {

    override suspend fun GetProductBrandListAsync(): Result<List<ProductBrandDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBrandListAsync"
        )
    }

    override suspend fun GetProductBrandByIdAsync(
        productBrandId: Int
    ): Result<ProductBrandUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBrandByIdAsync",
            query = "productBrandId=$productBrandId"
        )
    }

    override suspend fun GetProductBrandByIdExtendedAsync(
        productBrandId: Int
    ): Result<ProductBrandDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBrandByIdExtendedAsync",
            query = "productBrandId=$productBrandId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductBrandInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductBrandUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productBrandId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productBrandId=$productBrandId"
        )
    }
}