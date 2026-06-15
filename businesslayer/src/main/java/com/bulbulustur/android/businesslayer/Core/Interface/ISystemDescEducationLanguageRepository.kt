package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescEducationLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescEducationLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescEducationLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescEducationLanguageRepository {

    @GET("api/SystemDescEducationLanguage/GetSystemDescEducationLanguageListAsync")
    suspend fun GetSystemDescEducationLanguageListAsync():
            Result<List<SystemDescEducationLanguageDTO>>

    @GET("api/SystemDescEducationLanguage/GetSystemDescEducationLanguageByIdAsync")
    suspend fun GetSystemDescEducationLanguageByIdAsync(
        @Query("systemDescEducationLanguageId")
        systemDescEducationLanguageId: Int
    ): Result<SystemDescEducationLanguageUpdateModel?>

    @GET("api/SystemDescEducationLanguage/GetSystemDescEducationLanguageByIdExtendedAsync")
    suspend fun GetSystemDescEducationLanguageByIdExtendedAsync(
        @Query("systemDescEducationLanguageId")
        systemDescEducationLanguageId: Int
    ): Result<SystemDescEducationLanguageDTO?>

    @POST("api/SystemDescEducationLanguage/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescEducationLanguageInsertModel
    ): Result<Unit>

    @POST("api/SystemDescEducationLanguage/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescEducationLanguageUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescEducationLanguage/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescEducationLanguageId")
        systemDescEducationLanguageId: Int
    ): Result<Unit>
}
