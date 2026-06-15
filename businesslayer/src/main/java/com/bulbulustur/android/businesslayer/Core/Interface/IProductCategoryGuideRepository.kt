package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryGuideDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategoryGuideInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryGuideUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductCategoryGuideRepository {

    @GET("api/ProductCategoryGuide/GetProductCategoryGuideListAsync")
    suspend fun GetProductCategoryGuideListAsync():
            Result<List<ProductCategoryGuideDTO>>

    @GET("api/ProductCategoryGuide/GetProductCategoryGuideByIdAsync")
    suspend fun GetProductCategoryGuideByIdAsync(
        @Query("productCategoryGuideId")
        productCategoryGuideId: Int
    ): Result<ProductCategoryGuideUpdateModel?>

    @GET("api/ProductCategoryGuide/GetProductCategoryGuideByIdExtendedAsync")
    suspend fun GetProductCategoryGuideByIdExtendedAsync(
        @Query("productCategoryGuideId")
        productCategoryGuideId: Int
    ): Result<ProductCategoryGuideDTO?>

    @POST("api/ProductCategoryGuide/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductCategoryGuideInsertModel
    ): Result<Unit>

    @POST("api/ProductCategoryGuide/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductCategoryGuideUpdateModel
    ): Result<Unit>

    @POST("api/ProductCategoryGuide/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productCategoryGuideId")
        productCategoryGuideId: Int
    ): Result<Unit>
}
