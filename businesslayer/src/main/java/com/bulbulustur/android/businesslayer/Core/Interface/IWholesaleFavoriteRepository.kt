package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleFavoriteInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleFavoriteUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleFavoriteRepository {

    @GET("GetWholesaleFavoriteListAsync")
    suspend fun GetWholesaleFavoriteListAsync(@Query("memberId") memberId: Int, @Query("count") count: Int = 100): Result<List<WholesaleFavoriteDTO>>

    @GET("GetWholesaleFavoriteByIdAsync")
    suspend fun GetWholesaleFavoriteByIdAsync(@Query("wholesaleFavoriteId") wholesaleFavoriteId: Int): Result<WholesaleFavoriteUpdateModel?>

    @GET("GetWholesaleFavoriteByIdExtendedAsync")
    suspend fun GetWholesaleFavoriteByIdExtendedAsync(@Query("wholesaleFavoriteId") wholesaleFavoriteId: Int): Result<WholesaleFavoriteDTO?>

    @POST("InsertAsync")
    suspend fun InsertAsync(@Query("memberId") memberId: Int, @Body model: WholesaleFavoriteInsertModel): Result<Unit>

    @DELETE("DeleteAsync")
    suspend fun DeleteAsync(@Query("memberId") memberId: Int, @Query("wholesaleFavoriteId") wholesaleFavoriteId: Int): Result<Unit>
}