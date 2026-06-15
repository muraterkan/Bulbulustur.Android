package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CookieProviderDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CookieProviderInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CookieProviderUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICookieProviderRepository {

    @GET("api/CookieProvider/GetCookieProviderListAsync")
    suspend fun GetCookieProviderListAsync():
            Result<List<CookieProviderDTO>>

    @GET("api/CookieProvider/GetCookieProviderByIdAsync")
    suspend fun GetCookieProviderByIdAsync(
        @Query("cookieProviderId")
        cookieProviderId: Int
    ): Result<CookieProviderUpdateModel?>

    @GET("api/CookieProvider/GetCookieProviderByIdExtendedAsync")
    suspend fun GetCookieProviderByIdExtendedAsync(
        @Query("cookieProviderId")
        cookieProviderId: Int
    ): Result<CookieProviderDTO?>

    @POST("api/CookieProvider/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CookieProviderInsertModel
    ): Result<Unit>

    @POST("api/CookieProvider/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CookieProviderUpdateModel
    ): Result<Unit>

    @POST("api/CookieProvider/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("cookieProviderId")
        cookieProviderId: Int
    ): Result<Unit>
}
