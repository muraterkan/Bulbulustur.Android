package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.HelpDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IHelpRepository {

    @GET("api/Help/GetHelpListAsync")
    suspend fun GetHelpListAsync():
            Result<List<HelpDTO>>

    @GET("api/Help/GetHelpByIdAsync")
    suspend fun GetHelpByIdAsync(
        @Query("helpId")
        helpId: Int
    ): Result<HelpUpdateModel?>

    @GET("api/Help/GetHelpByIdExtendedAsync")
    suspend fun GetHelpByIdExtendedAsync(
        @Query("helpId")
        helpId: Int
    ): Result<HelpDTO?>

    @POST("api/Help/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: HelpInsertModel
    ): Result<Unit>

    @POST("api/Help/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: HelpUpdateModel
    ): Result<Unit>

    @POST("api/Help/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("helpId")
        helpId: Int
    ): Result<Unit>
}
