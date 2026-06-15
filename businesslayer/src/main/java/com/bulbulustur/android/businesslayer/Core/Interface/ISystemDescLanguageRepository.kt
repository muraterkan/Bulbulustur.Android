package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescLanguageRepository {

    @GET("api/SystemDescLanguage/GetSystemDescLanguageListAsync")
    suspend fun GetSystemDescLanguageListAsync():
            Result<List<SystemDescLanguageDTO>>

    @GET("api/SystemDescLanguage/GetSystemDescLanguageByIdAsync")
    suspend fun GetSystemDescLanguageByIdAsync(
        @Query("systemDescLanguageId")
        systemDescLanguageId: Int
    ): Result<SystemDescLanguageUpdateModel?>

    @GET("api/SystemDescLanguage/GetSystemDescLanguageByIdExtendedAsync")
    suspend fun GetSystemDescLanguageByIdExtendedAsync(
        @Query("systemDescLanguageId")
        systemDescLanguageId: Int
    ): Result<SystemDescLanguageDTO?>

    @POST("api/SystemDescLanguage/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescLanguageInsertModel
    ): Result<Unit>

    @POST("api/SystemDescLanguage/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescLanguageUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescLanguage/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescLanguageId")
        systemDescLanguageId: Int
    ): Result<Unit>
}
