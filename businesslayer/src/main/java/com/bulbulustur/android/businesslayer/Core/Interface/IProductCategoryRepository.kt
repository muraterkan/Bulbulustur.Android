package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductCategoryRepository {

    @GET("api/ProductCategory/GetProductCategoryListAsync")
    suspend fun GetProductCategoryListAsync():
            Result<List<ProductCategoryDTO>>

    @GET("api/ProductCategory/GetProductCategoryByIdAsync")
    suspend fun GetProductCategoryByIdAsync(
        @Query("productCategoryId")
        productCategoryId: Int
    ): Result<ProductCategoryUpdateModel?>

    @GET("api/ProductCategory/GetProductCategoryByIdExtendedAsync")
    suspend fun GetProductCategoryByIdExtendedAsync(
        @Query("productCategoryId")
        productCategoryId: Int
    ): Result<ProductCategoryDTO?>

    @POST("api/ProductCategory/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductCategoryInsertModel
    ): Result<Unit>

    @POST("api/ProductCategory/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductCategoryUpdateModel
    ): Result<Unit>

    @POST("api/ProductCategory/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productCategoryId")
        productCategoryId: Int
    ): Result<Unit>
}
