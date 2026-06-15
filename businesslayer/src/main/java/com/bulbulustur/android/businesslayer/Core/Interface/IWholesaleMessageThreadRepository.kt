package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageThreadDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleMessageThreadInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageThreadUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleMessageThreadRepository {

    @GET("api/WholesaleMessageThread/GetWholesaleMessageThreadListAsync")
    suspend fun GetWholesaleMessageThreadListAsync():
            Result<List<WholesaleMessageThreadDTO>>

    @GET("api/WholesaleMessageThread/GetWholesaleMessageThreadByIdAsync")
    suspend fun GetWholesaleMessageThreadByIdAsync(
        @Query("wholesaleMessageThreadId")
        wholesaleMessageThreadId: Int
    ): Result<WholesaleMessageThreadUpdateModel?>

    @GET("api/WholesaleMessageThread/GetWholesaleMessageThreadByIdExtendedAsync")
    suspend fun GetWholesaleMessageThreadByIdExtendedAsync(
        @Query("wholesaleMessageThreadId")
        wholesaleMessageThreadId: Int
    ): Result<WholesaleMessageThreadDTO?>

    @POST("api/WholesaleMessageThread/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleMessageThreadInsertModel
    ): Result<Unit>

    @POST("api/WholesaleMessageThread/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleMessageThreadUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleMessageThread/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleMessageThreadId")
        wholesaleMessageThreadId: Int
    ): Result<Unit>
}
