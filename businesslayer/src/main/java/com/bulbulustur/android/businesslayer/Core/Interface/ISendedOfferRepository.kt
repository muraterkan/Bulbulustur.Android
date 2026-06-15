package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SendedOfferDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SendedOfferInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SendedOfferUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISendedOfferRepository {

    @GET("api/SendedOffer/GetSendedOfferListAsync")
    suspend fun GetSendedOfferListAsync():
            Result<List<SendedOfferDTO>>

    @GET("api/SendedOffer/GetSendedOfferByIdAsync")
    suspend fun GetSendedOfferByIdAsync(
        @Query("sendedOfferId")
        sendedOfferId: Int
    ): Result<SendedOfferUpdateModel?>

    @GET("api/SendedOffer/GetSendedOfferByIdExtendedAsync")
    suspend fun GetSendedOfferByIdExtendedAsync(
        @Query("sendedOfferId")
        sendedOfferId: Int
    ): Result<SendedOfferDTO?>

    @POST("api/SendedOffer/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SendedOfferInsertModel
    ): Result<Unit>

    @POST("api/SendedOffer/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SendedOfferUpdateModel
    ): Result<Unit>

    @POST("api/SendedOffer/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("sendedOfferId")
        sendedOfferId: Int
    ): Result<Unit>
}
