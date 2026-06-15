package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductVariantInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductVariantRepository {

    @GET("api/ProductVariant/GetProductVariantListAsync")
    suspend fun GetProductVariantListAsync():
            Result<List<ProductVariantDTO>>

    @GET("api/ProductVariant/GetProductVariantByIdAsync")
    suspend fun GetProductVariantByIdAsync(
        @Query("productVariantId")
        productVariantId: Int
    ): Result<ProductVariantUpdateModel?>

    @GET("api/ProductVariant/GetProductVariantByIdExtendedAsync")
    suspend fun GetProductVariantByIdExtendedAsync(
        @Query("productVariantId")
        productVariantId: Int
    ): Result<ProductVariantDTO?>

    @POST("api/ProductVariant/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductVariantInsertModel
    ): Result<Unit>

    @POST("api/ProductVariant/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductVariantUpdateModel
    ): Result<Unit>

    @POST("api/ProductVariant/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productVariantId")
        productVariantId: Int
    ): Result<Unit>
}
