package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleFavoriteInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleFavoriteUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleFavoriteRepository {

    @GET("api/WholesaleFavorite/GetWholesaleFavoriteListAsync")
    suspend fun GetWholesaleFavoriteListAsync():
            Result<List<WholesaleFavoriteDTO>>

    @GET("api/WholesaleFavorite/GetWholesaleFavoriteByIdAsync")
    suspend fun GetWholesaleFavoriteByIdAsync(
        @Query("wholesaleFavoriteId")
        wholesaleFavoriteId: Int
    ): Result<WholesaleFavoriteUpdateModel?>

    @GET("api/WholesaleFavorite/GetWholesaleFavoriteByIdExtendedAsync")
    suspend fun GetWholesaleFavoriteByIdExtendedAsync(
        @Query("wholesaleFavoriteId")
        wholesaleFavoriteId: Int
    ): Result<WholesaleFavoriteDTO?>

    @POST("api/WholesaleFavorite/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleFavoriteInsertModel
    ): Result<Unit>

    @POST("api/WholesaleFavorite/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleFavoriteUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleFavorite/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleFavoriteId")
        wholesaleFavoriteId: Int
    ): Result<Unit>
}
