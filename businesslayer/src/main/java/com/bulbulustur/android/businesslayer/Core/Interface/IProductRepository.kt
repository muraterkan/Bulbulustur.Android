package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductRepository {

    @GET("api/Product/GetProductListAsync")
    suspend fun GetProductListAsync():
            Result<List<ProductDTO>>

    @GET("api/Product/GetProductByIdAsync")
    suspend fun GetProductByIdAsync(
        @Query("productId")
        productId: Int
    ): Result<ProductUpdateModel?>

    @GET("api/Product/GetProductByIdExtendedAsync")
    suspend fun GetProductByIdExtendedAsync(
        @Query("productId")
        productId: Int
    ): Result<ProductDTO?>

    @POST("api/Product/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductInsertModel
    ): Result<Unit>

    @POST("api/Product/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductUpdateModel
    ): Result<Unit>

    @POST("api/Product/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productId")
        productId: Int
    ): Result<Unit>
}
