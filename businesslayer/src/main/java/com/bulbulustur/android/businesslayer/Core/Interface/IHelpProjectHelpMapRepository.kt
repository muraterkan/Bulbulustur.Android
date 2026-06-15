package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.HelpProjectHelpMapDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpProjectHelpMapInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpProjectHelpMapUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IHelpProjectHelpMapRepository {

    @GET("api/HelpProjectHelpMap/GetHelpProjectHelpMapListAsync")
    suspend fun GetHelpProjectHelpMapListAsync():
            Result<List<HelpProjectHelpMapDTO>>

    @GET("api/HelpProjectHelpMap/GetHelpProjectHelpMapByIdAsync")
    suspend fun GetHelpProjectHelpMapByIdAsync(
        @Query("helpProjectHelpMapId")
        helpProjectHelpMapId: Int
    ): Result<HelpProjectHelpMapUpdateModel?>

    @GET("api/HelpProjectHelpMap/GetHelpProjectHelpMapByIdExtendedAsync")
    suspend fun GetHelpProjectHelpMapByIdExtendedAsync(
        @Query("helpProjectHelpMapId")
        helpProjectHelpMapId: Int
    ): Result<HelpProjectHelpMapDTO?>

    @POST("api/HelpProjectHelpMap/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: HelpProjectHelpMapInsertModel
    ): Result<Unit>

    @POST("api/HelpProjectHelpMap/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: HelpProjectHelpMapUpdateModel
    ): Result<Unit>

    @POST("api/HelpProjectHelpMap/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("helpProjectHelpMapId")
        helpProjectHelpMapId: Int
    ): Result<Unit>
}
