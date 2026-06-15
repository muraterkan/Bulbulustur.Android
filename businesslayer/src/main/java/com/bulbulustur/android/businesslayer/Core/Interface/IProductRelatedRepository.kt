package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductRelatedDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductRelatedInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductRelatedUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductRelatedRepository {

    @GET("api/ProductRelated/GetProductRelatedListAsync")
    suspend fun GetProductRelatedListAsync():
            Result<List<ProductRelatedDTO>>

    @GET("api/ProductRelated/GetProductRelatedByIdAsync")
    suspend fun GetProductRelatedByIdAsync(
        @Query("productRelatedId")
        productRelatedId: Int
    ): Result<ProductRelatedUpdateModel?>

    @GET("api/ProductRelated/GetProductRelatedByIdExtendedAsync")
    suspend fun GetProductRelatedByIdExtendedAsync(
        @Query("productRelatedId")
        productRelatedId: Int
    ): Result<ProductRelatedDTO?>

    @POST("api/ProductRelated/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductRelatedInsertModel
    ): Result<Unit>

    @POST("api/ProductRelated/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductRelatedUpdateModel
    ): Result<Unit>

    @POST("api/ProductRelated/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productRelatedId")
        productRelatedId: Int
    ): Result<Unit>
}
