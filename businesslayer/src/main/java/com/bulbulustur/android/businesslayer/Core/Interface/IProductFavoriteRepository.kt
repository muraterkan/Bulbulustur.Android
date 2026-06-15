package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductFavoriteInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductFavoriteUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductFavoriteRepository {

    @GET("api/ProductFavorite/GetProductFavoriteListAsync")
    suspend fun GetProductFavoriteListAsync():
            Result<List<ProductFavoriteDTO>>

    @GET("api/ProductFavorite/GetProductFavoriteByIdAsync")
    suspend fun GetProductFavoriteByIdAsync(
        @Query("productFavoriteId")
        productFavoriteId: Int
    ): Result<ProductFavoriteUpdateModel?>

    @GET("api/ProductFavorite/GetProductFavoriteByIdExtendedAsync")
    suspend fun GetProductFavoriteByIdExtendedAsync(
        @Query("productFavoriteId")
        productFavoriteId: Int
    ): Result<ProductFavoriteDTO?>

    @POST("api/ProductFavorite/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductFavoriteInsertModel
    ): Result<Unit>

    @POST("api/ProductFavorite/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductFavoriteUpdateModel
    ): Result<Unit>

    @POST("api/ProductFavorite/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productFavoriteId")
        productFavoriteId: Int
    ): Result<Unit>
}
