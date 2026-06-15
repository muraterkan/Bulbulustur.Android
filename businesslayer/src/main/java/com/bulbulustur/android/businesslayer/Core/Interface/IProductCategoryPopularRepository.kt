package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryPopularDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategoryPopularInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryPopularUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductCategoryPopularRepository {

    @GET("api/ProductCategoryPopular/GetProductCategoryPopularListAsync")
    suspend fun GetProductCategoryPopularListAsync():
            Result<List<ProductCategoryPopularDTO>>

    @GET("api/ProductCategoryPopular/GetProductCategoryPopularByIdAsync")
    suspend fun GetProductCategoryPopularByIdAsync(
        @Query("productCategoryPopularId")
        productCategoryPopularId: Int
    ): Result<ProductCategoryPopularUpdateModel?>

    @GET("api/ProductCategoryPopular/GetProductCategoryPopularByIdExtendedAsync")
    suspend fun GetProductCategoryPopularByIdExtendedAsync(
        @Query("productCategoryPopularId")
        productCategoryPopularId: Int
    ): Result<ProductCategoryPopularDTO?>

    @POST("api/ProductCategoryPopular/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductCategoryPopularInsertModel
    ): Result<Unit>

    @POST("api/ProductCategoryPopular/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductCategoryPopularUpdateModel
    ): Result<Unit>

    @POST("api/ProductCategoryPopular/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productCategoryPopularId")
        productCategoryPopularId: Int
    ): Result<Unit>
}
