package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPriceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductVariantPriceRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductVariantPriceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantPriceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductVariantPriceRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductVariantPriceRepository {

    override suspend fun GetProductVariantPriceListAsync(): Result<List<ProductVariantPriceDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductVariantPriceListAsync"
        )
    }

    override suspend fun GetProductVariantPriceByIdAsync(
        productVariantPriceId: Int
    ): Result<ProductVariantPriceUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductVariantPriceByIdAsync",
            query = "productVariantPriceId=$productVariantPriceId"
        )
    }

    override suspend fun GetProductVariantPriceByIdExtendedAsync(
        productVariantPriceId: Int
    ): Result<ProductVariantPriceDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductVariantPriceByIdExtendedAsync",
            query = "productVariantPriceId=$productVariantPriceId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductVariantPriceInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductVariantPriceUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productVariantPriceId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productVariantPriceId=$productVariantPriceId"
        )
    }
}