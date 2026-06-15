package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleMessageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleMessageRepository {

    @GET("api/WholesaleMessage/GetWholesaleMessageListAsync")
    suspend fun GetWholesaleMessageListAsync():
            Result<List<WholesaleMessageDTO>>

    @GET("api/WholesaleMessage/GetWholesaleMessageByIdAsync")
    suspend fun GetWholesaleMessageByIdAsync(
        @Query("wholesaleMessageId")
        wholesaleMessageId: Int
    ): Result<WholesaleMessageUpdateModel?>

    @GET("api/WholesaleMessage/GetWholesaleMessageByIdExtendedAsync")
    suspend fun GetWholesaleMessageByIdExtendedAsync(
        @Query("wholesaleMessageId")
        wholesaleMessageId: Int
    ): Result<WholesaleMessageDTO?>

    @POST("api/WholesaleMessage/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleMessageInsertModel
    ): Result<Unit>

    @POST("api/WholesaleMessage/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleMessageUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleMessage/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleMessageId")
        wholesaleMessageId: Int
    ): Result<Unit>
}
