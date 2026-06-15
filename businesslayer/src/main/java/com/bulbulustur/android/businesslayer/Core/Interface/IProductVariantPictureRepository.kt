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

    @GET("api/ProductVariantPicture/GetProductVariantPictureListAsync")
    suspend fun GetProductVariantPictureListAsync():
            Result<List<ProductVariantPictureDTO>>

    @GET("api/ProductVariantPicture/GetProductVariantPictureByIdAsync")
    suspend fun GetProductVariantPictureByIdAsync(
        @Query("productVariantPictureId")
        productVariantPictureId: Int
    ): Result<ProductVariantPictureUpdateModel?>

    @GET("api/ProductVariantPicture/GetProductVariantPictureByIdExtendedAsync")
    suspend fun GetProductVariantPictureByIdExtendedAsync(
        @Query("productVariantPictureId")
        productVariantPictureId: Int
    ): Result<ProductVariantPictureDTO?>

    @POST("api/ProductVariantPicture/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductVariantPictureInsertModel
    ): Result<Unit>

    @POST("api/ProductVariantPicture/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductVariantPictureUpdateModel
    ): Result<Unit>

    @POST("api/ProductVariantPicture/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productVariantPictureId")
        productVariantPictureId: Int
    ): Result<Unit>
}
