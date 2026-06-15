package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductAboutDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductAboutInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductAboutUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductAboutRepository {

    @GET("api/ProductAbout/GetProductAboutListAsync")
    suspend fun GetProductAboutListAsync():
            Result<List<ProductAboutDTO>>

    @GET("api/ProductAbout/GetProductAboutByIdAsync")
    suspend fun GetProductAboutByIdAsync(
        @Query("productAboutId")
        productAboutId: Int
    ): Result<ProductAboutUpdateModel?>

    @GET("api/ProductAbout/GetProductAboutByIdExtendedAsync")
    suspend fun GetProductAboutByIdExtendedAsync(
        @Query("productAboutId")
        productAboutId: Int
    ): Result<ProductAboutDTO?>

    @POST("api/ProductAbout/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductAboutInsertModel
    ): Result<Unit>

    @POST("api/ProductAbout/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductAboutUpdateModel
    ): Result<Unit>

    @POST("api/ProductAbout/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productAboutId")
        productAboutId: Int
    ): Result<Unit>
}
