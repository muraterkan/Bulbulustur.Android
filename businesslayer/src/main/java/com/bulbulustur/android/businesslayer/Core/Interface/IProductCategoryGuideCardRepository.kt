package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryGuideCardDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategoryGuideCardInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryGuideCardUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductCategoryGuideCardRepository {

    @GET("api/ProductCategoryGuideCard/GetProductCategoryGuideCardListAsync")
    suspend fun GetProductCategoryGuideCardListAsync():
            Result<List<ProductCategoryGuideCardDTO>>

    @GET("api/ProductCategoryGuideCard/GetProductCategoryGuideCardByIdAsync")
    suspend fun GetProductCategoryGuideCardByIdAsync(
        @Query("productCategoryGuideCardId")
        productCategoryGuideCardId: Int
    ): Result<ProductCategoryGuideCardUpdateModel?>

    @GET("api/ProductCategoryGuideCard/GetProductCategoryGuideCardByIdExtendedAsync")
    suspend fun GetProductCategoryGuideCardByIdExtendedAsync(
        @Query("productCategoryGuideCardId")
        productCategoryGuideCardId: Int
    ): Result<ProductCategoryGuideCardDTO?>

    @POST("api/ProductCategoryGuideCard/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductCategoryGuideCardInsertModel
    ): Result<Unit>

    @POST("api/ProductCategoryGuideCard/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductCategoryGuideCardUpdateModel
    ): Result<Unit>

    @POST("api/ProductCategoryGuideCard/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productCategoryGuideCardId")
        productCategoryGuideCardId: Int
    ): Result<Unit>
}
