package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CookieConsentDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CookieConsentInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CookieConsentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICookieConsentRepository {

    @GET("api/CookieConsent/GetCookieConsentListAsync")
    suspend fun GetCookieConsentListAsync():
            Result<List<CookieConsentDTO>>

    @GET("api/CookieConsent/GetCookieConsentByIdAsync")
    suspend fun GetCookieConsentByIdAsync(
        @Query("cookieConsentId")
        cookieConsentId: Int
    ): Result<CookieConsentUpdateModel?>

    @GET("api/CookieConsent/GetCookieConsentByIdExtendedAsync")
    suspend fun GetCookieConsentByIdExtendedAsync(
        @Query("cookieConsentId")
        cookieConsentId: Int
    ): Result<CookieConsentDTO?>

    @POST("api/CookieConsent/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CookieConsentInsertModel
    ): Result<Unit>

    @POST("api/CookieConsent/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CookieConsentUpdateModel
    ): Result<Unit>

    @POST("api/CookieConsent/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("cookieConsentId")
        cookieConsentId: Int
    ): Result<Unit>
}
