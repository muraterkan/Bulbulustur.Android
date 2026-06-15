package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.HelpProjectDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpProjectInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpProjectUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IHelpProjectRepository {

    @GET("api/HelpProject/GetHelpProjectListAsync")
    suspend fun GetHelpProjectListAsync():
            Result<List<HelpProjectDTO>>

    @GET("api/HelpProject/GetHelpProjectByIdAsync")
    suspend fun GetHelpProjectByIdAsync(
        @Query("helpProjectId")
        helpProjectId: Int
    ): Result<HelpProjectUpdateModel?>

    @GET("api/HelpProject/GetHelpProjectByIdExtendedAsync")
    suspend fun GetHelpProjectByIdExtendedAsync(
        @Query("helpProjectId")
        helpProjectId: Int
    ): Result<HelpProjectDTO?>

    @POST("api/HelpProject/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: HelpProjectInsertModel
    ): Result<Unit>

    @POST("api/HelpProject/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: HelpProjectUpdateModel
    ): Result<Unit>

    @POST("api/HelpProject/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("helpProjectId")
        helpProjectId: Int
    ): Result<Unit>
}
