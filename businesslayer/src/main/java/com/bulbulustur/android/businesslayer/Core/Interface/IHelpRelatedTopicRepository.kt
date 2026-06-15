package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.HelpRelatedTopicDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpRelatedTopicInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpRelatedTopicUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IHelpRelatedTopicRepository {

    @GET("api/HelpRelatedTopic/GetHelpRelatedTopicListAsync")
    suspend fun GetHelpRelatedTopicListAsync():
            Result<List<HelpRelatedTopicDTO>>

    @GET("api/HelpRelatedTopic/GetHelpRelatedTopicByIdAsync")
    suspend fun GetHelpRelatedTopicByIdAsync(
        @Query("helpRelatedTopicId")
        helpRelatedTopicId: Int
    ): Result<HelpRelatedTopicUpdateModel?>

    @GET("api/HelpRelatedTopic/GetHelpRelatedTopicByIdExtendedAsync")
    suspend fun GetHelpRelatedTopicByIdExtendedAsync(
        @Query("helpRelatedTopicId")
        helpRelatedTopicId: Int
    ): Result<HelpRelatedTopicDTO?>

    @POST("api/HelpRelatedTopic/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: HelpRelatedTopicInsertModel
    ): Result<Unit>

    @POST("api/HelpRelatedTopic/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: HelpRelatedTopicUpdateModel
    ): Result<Unit>

    @POST("api/HelpRelatedTopic/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("helpRelatedTopicId")
        helpRelatedTopicId: Int
    ): Result<Unit>
}
