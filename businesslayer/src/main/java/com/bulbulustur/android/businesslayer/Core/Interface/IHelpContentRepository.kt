package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.HelpContentDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpContentInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpContentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IHelpContentRepository {

    @GET("api/HelpContent/GetHelpContentListAsync")
    suspend fun GetHelpContentListAsync():
            Result<List<HelpContentDTO>>

    @GET("api/HelpContent/GetHelpContentByIdAsync")
    suspend fun GetHelpContentByIdAsync(
        @Query("helpContentId")
        helpContentId: Int
    ): Result<HelpContentUpdateModel?>

    @GET("api/HelpContent/GetHelpContentByIdExtendedAsync")
    suspend fun GetHelpContentByIdExtendedAsync(
        @Query("helpContentId")
        helpContentId: Int
    ): Result<HelpContentDTO?>

    @POST("api/HelpContent/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: HelpContentInsertModel
    ): Result<Unit>

    @POST("api/HelpContent/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: HelpContentUpdateModel
    ): Result<Unit>

    @POST("api/HelpContent/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("helpContentId")
        helpContentId: Int
    ): Result<Unit>
}
