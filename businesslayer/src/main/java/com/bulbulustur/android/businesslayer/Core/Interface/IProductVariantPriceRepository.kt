package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPriceDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductVariantPriceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantPriceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductVariantPriceRepository {

    @GET("api/ProductVariantPrice/GetProductVariantPriceListAsync")
    suspend fun GetProductVariantPriceListAsync():
            Result<List<ProductVariantPriceDTO>>

    @GET("api/ProductVariantPrice/GetProductVariantPriceByIdAsync")
    suspend fun GetProductVariantPriceByIdAsync(
        @Query("productVariantPriceId")
        productVariantPriceId: Int
    ): Result<ProductVariantPriceUpdateModel?>

    @GET("api/ProductVariantPrice/GetProductVariantPriceByIdExtendedAsync")
    suspend fun GetProductVariantPriceByIdExtendedAsync(
        @Query("productVariantPriceId")
        productVariantPriceId: Int
    ): Result<ProductVariantPriceDTO?>

    @POST("api/ProductVariantPrice/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductVariantPriceInsertModel
    ): Result<Unit>

    @POST("api/ProductVariantPrice/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductVariantPriceUpdateModel
    ): Result<Unit>

    @POST("api/ProductVariantPrice/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productVariantPriceId")
        productVariantPriceId: Int
    ): Result<Unit>
}
