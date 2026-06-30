package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPictureDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductVariantPictureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductVariantPictureRepository {

    @GET("GetProductVariantPicturesAsync")
    suspend fun GetProductVariantPicturesAsync(
        @Query("variantId")
        variantId: Int,
        @Query("count")
        count: Int = 10
    ): Result<List<ProductVariantPictureDTO>>

    @GET("GetProductVariantPictureByIdAsync")
    suspend fun GetProductVariantPictureByIdAsync(
        @Query("productVariantPictureId")
        productVariantPictureId: Int
    ): Result<ProductVariantPictureUpdateModel?>

    @GET("GetProductVariantPictureByIdExtendedAsync")
    suspend fun GetProductVariantPictureByIdExtendedAsync(
        @Query("productVariantPictureId")
        productVariantPictureId: Int
    ): Result<ProductVariantPictureDTO?>
}