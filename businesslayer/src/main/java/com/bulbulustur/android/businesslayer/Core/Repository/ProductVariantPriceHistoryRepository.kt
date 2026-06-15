package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPriceHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductVariantPriceHistoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductVariantPriceHistoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantPriceHistoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductVariantPriceHistoryRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductVariantPriceHistoryRepository {

    override suspend fun GetProductVariantPriceHistoryListAsync(): Result<List<ProductVariantPriceHistoryDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductVariantPriceHistoryListAsync"
        )
    }

    override suspend fun GetProductVariantPriceHistoryByIdAsync(
        productVariantPriceHistoryId: Int
    ): Result<ProductVariantPriceHistoryUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductVariantPriceHistoryByIdAsync",
            query = "productVariantPriceHistoryId=$productVariantPriceHistoryId"
        )
    }

    override suspend fun GetProductVariantPriceHistoryByIdExtendedAsync(
        productVariantPriceHistoryId: Int
    ): Result<ProductVariantPriceHistoryDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductVariantPriceHistoryByIdExtendedAsync",
            query = "productVariantPriceHistoryId=$productVariantPriceHistoryId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductVariantPriceHistoryInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductVariantPriceHistoryUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productVariantPriceHistoryId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productVariantPriceHistoryId=$productVariantPriceHistoryId"
        )
    }
}