package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductFavoriteInsertModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductFavoriteRepository {

    @GET("GetProductFavoritesAsync")
    suspend fun GetProductFavoritesAsync(@Query("memberId") memberId: Int, @Query("count") count: Int = 100): Result<List<ProductFavoriteDTO>>

    @POST("InsertProductFavoriteAsync")
    suspend fun InsertProductFavoriteAsync(@Query("memberId") memberId: Int, @Body model: ProductFavoriteInsertModel): Result<Unit>

    @DELETE("DeleteProductFavoriteAsync")
    suspend fun DeleteProductFavoriteAsync(@Query("memberId") memberId: Int, @Query("favoriteId") favoriteId: Int): Result<Unit>

    @POST("MoveProductFavoriteToBasketAsync")
    suspend fun MoveProductFavoriteToBasketAsync(@Query("memberId") memberId: Int, @Query("favoriteId") favoriteId: Int): Result<Unit>
}