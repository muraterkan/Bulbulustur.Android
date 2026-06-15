package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPriceHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductVariantPriceHistoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantPriceHistoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductVariantPriceHistoryRepository {

    @GET("api/ProductVariantPriceHistory/GetProductVariantPriceHistoryListAsync")
    suspend fun GetProductVariantPriceHistoryListAsync():
            Result<List<ProductVariantPriceHistoryDTO>>

    @GET("api/ProductVariantPriceHistory/GetProductVariantPriceHistoryByIdAsync")
    suspend fun GetProductVariantPriceHistoryByIdAsync(
        @Query("productVariantPriceHistoryId")
        productVariantPriceHistoryId: Int
    ): Result<ProductVariantPriceHistoryUpdateModel?>

    @GET("api/ProductVariantPriceHistory/GetProductVariantPriceHistoryByIdExtendedAsync")
    suspend fun GetProductVariantPriceHistoryByIdExtendedAsync(
        @Query("productVariantPriceHistoryId")
        productVariantPriceHistoryId: Int
    ): Result<ProductVariantPriceHistoryDTO?>

    @POST("api/ProductVariantPriceHistory/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductVariantPriceHistoryInsertModel
    ): Result<Unit>

    @POST("api/ProductVariantPriceHistory/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductVariantPriceHistoryUpdateModel
    ): Result<Unit>

    @POST("api/ProductVariantPriceHistory/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productVariantPriceHistoryId")
        productVariantPriceHistoryId: Int
    ): Result<Unit>
}
