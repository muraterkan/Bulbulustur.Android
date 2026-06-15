package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductSizeTypeByCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductSizeTypeByCategoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductSizeTypeByCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductSizeTypeByCategoryRepository {

    @GET("api/ProductSizeTypeByCategory/GetProductSizeTypeByCategoryListAsync")
    suspend fun GetProductSizeTypeByCategoryListAsync():
            Result<List<ProductSizeTypeByCategoryDTO>>

    @GET("api/ProductSizeTypeByCategory/GetProductSizeTypeByCategoryByIdAsync")
    suspend fun GetProductSizeTypeByCategoryByIdAsync(
        @Query("productSizeTypeByCategoryId")
        productSizeTypeByCategoryId: Int
    ): Result<ProductSizeTypeByCategoryUpdateModel?>

    @GET("api/ProductSizeTypeByCategory/GetProductSizeTypeByCategoryByIdExtendedAsync")
    suspend fun GetProductSizeTypeByCategoryByIdExtendedAsync(
        @Query("productSizeTypeByCategoryId")
        productSizeTypeByCategoryId: Int
    ): Result<ProductSizeTypeByCategoryDTO?>

    @POST("api/ProductSizeTypeByCategory/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductSizeTypeByCategoryInsertModel
    ): Result<Unit>

    @POST("api/ProductSizeTypeByCategory/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductSizeTypeByCategoryUpdateModel
    ): Result<Unit>

    @POST("api/ProductSizeTypeByCategory/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productSizeTypeByCategoryId")
        productSizeTypeByCategoryId: Int
    ): Result<Unit>
}
