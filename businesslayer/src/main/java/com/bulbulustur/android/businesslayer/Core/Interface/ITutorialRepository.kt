package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TutorialDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TutorialInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TutorialUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ITutorialRepository {

    @GET("api/Tutorial/GetTutorialListAsync")
    suspend fun GetTutorialListAsync():
            Result<List<TutorialDTO>>

    @GET("api/Tutorial/GetTutorialByIdAsync")
    suspend fun GetTutorialByIdAsync(
        @Query("tutorialId")
        tutorialId: Int
    ): Result<TutorialUpdateModel?>

    @GET("api/Tutorial/GetTutorialByIdExtendedAsync")
    suspend fun GetTutorialByIdExtendedAsync(
        @Query("tutorialId")
        tutorialId: Int
    ): Result<TutorialDTO?>

    @POST("api/Tutorial/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: TutorialInsertModel
    ): Result<Unit>

    @POST("api/Tutorial/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: TutorialUpdateModel
    ): Result<Unit>

    @POST("api/Tutorial/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("tutorialId")
        tutorialId: Int
    ): Result<Unit>
}
