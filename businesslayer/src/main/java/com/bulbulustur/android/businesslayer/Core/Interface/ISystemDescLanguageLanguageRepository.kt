package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescLanguageLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescLanguageLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescLanguageLanguageRepository {

    @GET("api/SystemDescLanguageLanguage/GetSystemDescLanguageLanguageListAsync")
    suspend fun GetSystemDescLanguageLanguageListAsync():
            Result<List<SystemDescLanguageLanguageDTO>>

    @GET("api/SystemDescLanguageLanguage/GetSystemDescLanguageLanguageByIdAsync")
    suspend fun GetSystemDescLanguageLanguageByIdAsync(
        @Query("systemDescLanguageLanguageId")
        systemDescLanguageLanguageId: Int
    ): Result<SystemDescLanguageLanguageUpdateModel?>

    @GET("api/SystemDescLanguageLanguage/GetSystemDescLanguageLanguageByIdExtendedAsync")
    suspend fun GetSystemDescLanguageLanguageByIdExtendedAsync(
        @Query("systemDescLanguageLanguageId")
        systemDescLanguageLanguageId: Int
    ): Result<SystemDescLanguageLanguageDTO?>

    @POST("api/SystemDescLanguageLanguage/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescLanguageLanguageInsertModel
    ): Result<Unit>

    @POST("api/SystemDescLanguageLanguage/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescLanguageLanguageUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescLanguageLanguage/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescLanguageLanguageId")
        systemDescLanguageLanguageId: Int
    ): Result<Unit>
}
