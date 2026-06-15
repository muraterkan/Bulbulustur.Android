package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.HelpPopularTopicDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpPopularTopicInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpPopularTopicUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IHelpPopularTopicRepository {

    @GET("api/HelpPopularTopic/GetHelpPopularTopicListAsync")
    suspend fun GetHelpPopularTopicListAsync():
            Result<List<HelpPopularTopicDTO>>

    @GET("api/HelpPopularTopic/GetHelpPopularTopicByIdAsync")
    suspend fun GetHelpPopularTopicByIdAsync(
        @Query("helpPopularTopicId")
        helpPopularTopicId: Int
    ): Result<HelpPopularTopicUpdateModel?>

    @GET("api/HelpPopularTopic/GetHelpPopularTopicByIdExtendedAsync")
    suspend fun GetHelpPopularTopicByIdExtendedAsync(
        @Query("helpPopularTopicId")
        helpPopularTopicId: Int
    ): Result<HelpPopularTopicDTO?>

    @POST("api/HelpPopularTopic/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: HelpPopularTopicInsertModel
    ): Result<Unit>

    @POST("api/HelpPopularTopic/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: HelpPopularTopicUpdateModel
    ): Result<Unit>

    @POST("api/HelpPopularTopic/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("helpPopularTopicId")
        helpPopularTopicId: Int
    ): Result<Unit>
}
