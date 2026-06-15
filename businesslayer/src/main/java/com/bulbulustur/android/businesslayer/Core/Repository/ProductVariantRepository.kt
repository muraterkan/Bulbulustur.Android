package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductVariantRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductVariantInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductVariantRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductVariantRepository {

    override suspend fun GetProductVariantListAsync(): Result<List<ProductVariantDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductVariantListAsync"
        )
    }

    override suspend fun GetProductVariantByIdAsync(
        productVariantId: Int
    ): Result<ProductVariantUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductVariantByIdAsync",
            query = "productVariantId=$productVariantId"
        )
    }

    override suspend fun GetProductVariantByIdExtendedAsync(
        productVariantId: Int
    ): Result<ProductVariantDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductVariantByIdExtendedAsync",
            query = "productVariantId=$productVariantId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductVariantInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductVariantUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productVariantId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productVariantId=$productVariantId"
        )
    }
}