package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageThreadParticipantDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleMessageThreadParticipantInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageThreadParticipantUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleMessageThreadParticipantRepository {

    @GET("api/WholesaleMessageThreadParticipant/GetWholesaleMessageThreadParticipantListAsync")
    suspend fun GetWholesaleMessageThreadParticipantListAsync():
            Result<List<WholesaleMessageThreadParticipantDTO>>

    @GET("api/WholesaleMessageThreadParticipant/GetWholesaleMessageThreadParticipantByIdAsync")
    suspend fun GetWholesaleMessageThreadParticipantByIdAsync(
        @Query("wholesaleMessageThreadParticipantId")
        wholesaleMessageThreadParticipantId: Int
    ): Result<WholesaleMessageThreadParticipantUpdateModel?>

    @GET("api/WholesaleMessageThreadParticipant/GetWholesaleMessageThreadParticipantByIdExtendedAsync")
    suspend fun GetWholesaleMessageThreadParticipantByIdExtendedAsync(
        @Query("wholesaleMessageThreadParticipantId")
        wholesaleMessageThreadParticipantId: Int
    ): Result<WholesaleMessageThreadParticipantDTO?>

    @POST("api/WholesaleMessageThreadParticipant/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleMessageThreadParticipantInsertModel
    ): Result<Unit>

    @POST("api/WholesaleMessageThreadParticipant/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleMessageThreadParticipantUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleMessageThreadParticipant/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleMessageThreadParticipantId")
        wholesaleMessageThreadParticipantId: Int
    ): Result<Unit>
}
