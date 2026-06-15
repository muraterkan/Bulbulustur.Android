package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.HelpFeedbackDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpFeedbackInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpFeedbackUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IHelpFeedbackRepository {

    @GET("api/HelpFeedback/GetHelpFeedbackListAsync")
    suspend fun GetHelpFeedbackListAsync():
            Result<List<HelpFeedbackDTO>>

    @GET("api/HelpFeedback/GetHelpFeedbackByIdAsync")
    suspend fun GetHelpFeedbackByIdAsync(
        @Query("helpFeedbackId")
        helpFeedbackId: Int
    ): Result<HelpFeedbackUpdateModel?>

    @GET("api/HelpFeedback/GetHelpFeedbackByIdExtendedAsync")
    suspend fun GetHelpFeedbackByIdExtendedAsync(
        @Query("helpFeedbackId")
        helpFeedbackId: Int
    ): Result<HelpFeedbackDTO?>

    @POST("api/HelpFeedback/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: HelpFeedbackInsertModel
    ): Result<Unit>

    @POST("api/HelpFeedback/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: HelpFeedbackUpdateModel
    ): Result<Unit>

    @POST("api/HelpFeedback/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("helpFeedbackId")
        helpFeedbackId: Int
    ): Result<Unit>
}
