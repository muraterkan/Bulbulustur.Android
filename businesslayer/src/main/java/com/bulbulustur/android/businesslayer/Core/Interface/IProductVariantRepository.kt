package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface IProductVariantRepository {

    @GET("GetProductVariantsAsync")
    suspend fun GetProductVariantsAsync(
        @Query("languageId")
        languageId: Int,

        @Query("productId")
        productId: Int,

        @Query("storeId")
        storeId: Int,

        @Query("count")
        count: Int = 100
    ): Result<List<ProductVariantDTO>>

    @GET("GetProductVariantByIdAsync")
    suspend fun GetProductVariantByIdAsync(
        @Query("variantId")
        variantId: Int
    ): Result<ProductVariantUpdateModel?>

    @GET("GetProductVariantByIdExtendedAsync")
    suspend fun GetProductVariantByIdExtendedAsync(
        @Query("languageId")
        languageId: Int,

        @Query("variantId")
        variantId: Int
    ): Result<ProductVariantDTO?>

    @GET("GetSmallestPriceWithStoreInfoAsync")
    suspend fun GetSmallestPriceWithStoreInfoAsync(
        @Query("languageId")
        languageId: Int,

        @Query("productId")
        productId: Int
    ): Result<ProductVariantDTO?>

    @GET("GetOtherStorePriceAsync")
    suspend fun GetOtherStorePriceAsync(
        @Query("languageId")
        languageId: Int,

        @Query("productId")
        productId: Int,

        @Query("variantId")
        variantId: Int,

        @Query("storeId")
        storeId: Int
    ): Result<List<ProductVariantDTO>>

    @GET("GetProductVariantOfStoreAsync")
    suspend fun GetProductVariantOfStoreAsync(
        @Query("languageId")
        languageId: Int,

        @Query("productId")
        productId: Int
    ): Result<List<ProductVariantDTO>>

    @GET("GetProductColorVariantsAsync")
    suspend fun GetProductColorVariantsAsync(
        @Query("languageId")
        languageId: Int,

        @Query("productId")
        productId: Int,

        @Query("variantId")
        variantId: Int
    ): Result<List<ProductVariantDTO>>

    @GET("GetProductSizeVariantsAsync")
    suspend fun GetProductSizeVariantsAsync(
        @Query("languageId")
        languageId: Int,

        @Query("productId")
        productId: Int,

        @Query("variantId")
        variantId: Int
    ): Result<List<ProductVariantDTO>>
}