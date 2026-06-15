package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCurrencyLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCurrencyLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCurrencyLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescCurrencyLanguageRepository {

    @GET("api/SystemDescCurrencyLanguage/GetSystemDescCurrencyLanguageListAsync")
    suspend fun GetSystemDescCurrencyLanguageListAsync():
            Result<List<SystemDescCurrencyLanguageDTO>>

    @GET("api/SystemDescCurrencyLanguage/GetSystemDescCurrencyLanguageByIdAsync")
    suspend fun GetSystemDescCurrencyLanguageByIdAsync(
        @Query("systemDescCurrencyLanguageId")
        systemDescCurrencyLanguageId: Int
    ): Result<SystemDescCurrencyLanguageUpdateModel?>

    @GET("api/SystemDescCurrencyLanguage/GetSystemDescCurrencyLanguageByIdExtendedAsync")
    suspend fun GetSystemDescCurrencyLanguageByIdExtendedAsync(
        @Query("systemDescCurrencyLanguageId")
        systemDescCurrencyLanguageId: Int
    ): Result<SystemDescCurrencyLanguageDTO?>

    @POST("api/SystemDescCurrencyLanguage/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescCurrencyLanguageInsertModel
    ): Result<Unit>

    @POST("api/SystemDescCurrencyLanguage/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescCurrencyLanguageUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescCurrencyLanguage/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescCurrencyLanguageId")
        systemDescCurrencyLanguageId: Int
    ): Result<Unit>
}
