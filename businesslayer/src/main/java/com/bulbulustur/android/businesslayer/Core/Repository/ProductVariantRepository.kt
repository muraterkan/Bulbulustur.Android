package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductVariantRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductVariantRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductVariantRepository {

    override suspend fun GetProductVariantsAsync(
        languageId: Int,
        productId: Int,
        storeId: Int,
        count: Int
    ): Result<List<ProductVariantDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_VARIANT_BASE_URL,
            method = "GetProductVariantsAsync",
            query =
                "languageId=$languageId" +
                        "&productId=$productId" +
                        "&storeId=$storeId" +
                        "&count=$count"
        )
    }

    override suspend fun GetProductVariantByIdAsync(
        variantId: Int
    ): Result<ProductVariantUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_VARIANT_BASE_URL,
            method = "GetProductVariantByIdAsync",
            query = "variantId=$variantId"
        )
    }

    override suspend fun GetProductVariantByIdExtendedAsync(
        languageId: Int,
        variantId: Int
    ): Result<ProductVariantDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_VARIANT_BASE_URL,
            method = "GetProductVariantByIdExtendedAsync",
            query =
                "languageId=$languageId" +
                        "&variantId=$variantId"
        )
    }

    override suspend fun GetSmallestPriceWithStoreInfoAsync(
        languageId: Int,
        productId: Int
    ): Result<ProductVariantDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_VARIANT_BASE_URL,
            method = "GetSmallestPriceWithStoreInfoAsync",
            query =
                "languageId=$languageId" +
                        "&productId=$productId"
        )
    }

    override suspend fun GetOtherStorePriceAsync(
        languageId: Int,
        productId: Int,
        variantId: Int,
        storeId: Int
    ): Result<List<ProductVariantDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_VARIANT_BASE_URL,
            method = "GetOtherStorePriceAsync",
            query =
                "languageId=$languageId" +
                        "&productId=$productId" +
                        "&variantId=$variantId" +
                        "&storeId=$storeId"
        )
    }

    override suspend fun GetProductVariantOfStoreAsync(
        languageId: Int,
        productId: Int
    ): Result<List<ProductVariantDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_VARIANT_BASE_URL,
            method = "GetProductVariantOfStoreAsync",
            query =
                "languageId=$languageId" +
                        "&productId=$productId"
        )
    }

    override suspend fun GetProductColorVariantsAsync(
        languageId: Int,
        productId: Int,
        variantId: Int
    ): Result<List<ProductVariantDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_VARIANT_BASE_URL,
            method = "GetProductColorVariantsAsync",
            query =
                "languageId=$languageId" +
                        "&productId=$productId" +
                        "&variantId=$variantId"
        )
    }

    override suspend fun GetProductSizeVariantsAsync(
        languageId: Int,
        productId: Int,
        variantId: Int
    ): Result<List<ProductVariantDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_VARIANT_BASE_URL,
            method = "GetProductSizeVariantsAsync",
            query =
                "languageId=$languageId" +
                        "&productId=$productId" +
                        "&variantId=$variantId"
        )
    }
}