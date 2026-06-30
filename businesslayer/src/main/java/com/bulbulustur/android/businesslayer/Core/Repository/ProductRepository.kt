package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.B2CProductDataDTO
import com.bulbulustur.android.businesslayer.Core.DTO.B2CProductFilterDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductRepository {

    override suspend fun GetProductDataAsync(
        filters: B2CProductFilterDTO,
        page: Int,
        pageSize: Int
    ): Result<B2CProductDataDTO> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_BASE_URL,
            method =
                "GetProductDataAsync" +
                        "?page=$page" +
                        "&pageSize=$pageSize",
            data = filters
        )
    }

    override suspend fun GetProductByIdAsync(
        productId: Int
    ): Result<ProductUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_BASE_URL,
            method = "GetProductByIdAsync",
            query = "productId=$productId"
        )
    }

    override suspend fun GetProductByIdExtendedAsync(
        languageId: Int,
        storeId: Int,
        productId: Int,
        variantId: Int
    ): Result<ProductDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_BASE_URL,
            method = "GetProductByIdExtendedAsync",
            query =
                "languageId=$languageId" +
                        "&storeId=$storeId" +
                        "&productId=$productId" +
                        "&variantId=$variantId"
        )
    }

    override suspend fun GetStoreProductDataAsync(
        storeId: Int,
        filters: B2CProductFilterDTO,
        page: Int,
        pageSize: Int
    ): Result<B2CProductDataDTO> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_BASE_URL,
            method =
                "GetStoreProductDataAsync" +
                        "?storeId=$storeId" +
                        "&page=$page" +
                        "&pageSize=$pageSize",
            data = filters
        )
    }

    override suspend fun GetOtherStorePrices(
        languageId: Int,
        productId: Int,
        variantId: Int
    ): Result<List<ProductVariantDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_BASE_URL,
            method = "GetOtherStorePrices",
            query =
                "languageId=$languageId" +
                        "&productId=$productId" +
                        "&variantId=$variantId"
        )
    }
}