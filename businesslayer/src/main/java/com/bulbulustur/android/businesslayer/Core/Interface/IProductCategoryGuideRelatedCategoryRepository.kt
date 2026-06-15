package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryGuideRelatedCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategoryGuideRelatedCategoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryGuideRelatedCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductCategoryGuideRelatedCategoryRepository {

    @GET("api/ProductCategoryGuideRelatedCategory/GetProductCategoryGuideRelatedCategoryListAsync")
    suspend fun GetProductCategoryGuideRelatedCategoryListAsync():
            Result<List<ProductCategoryGuideRelatedCategoryDTO>>

    @GET("api/ProductCategoryGuideRelatedCategory/GetProductCategoryGuideRelatedCategoryByIdAsync")
    suspend fun GetProductCategoryGuideRelatedCategoryByIdAsync(
        @Query("productCategoryGuideRelatedCategoryId")
        productCategoryGuideRelatedCategoryId: Int
    ): Result<ProductCategoryGuideRelatedCategoryUpdateModel?>

    @GET("api/ProductCategoryGuideRelatedCategory/GetProductCategoryGuideRelatedCategoryByIdExtendedAsync")
    suspend fun GetProductCategoryGuideRelatedCategoryByIdExtendedAsync(
        @Query("productCategoryGuideRelatedCategoryId")
        productCategoryGuideRelatedCategoryId: Int
    ): Result<ProductCategoryGuideRelatedCategoryDTO?>

    @POST("api/ProductCategoryGuideRelatedCategory/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductCategoryGuideRelatedCategoryInsertModel
    ): Result<Unit>

    @POST("api/ProductCategoryGuideRelatedCategory/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductCategoryGuideRelatedCategoryUpdateModel
    ): Result<Unit>

    @POST("api/ProductCategoryGuideRelatedCategory/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productCategoryGuideRelatedCategoryId")
        productCategoryGuideRelatedCategoryId: Int
    ): Result<Unit>
}
