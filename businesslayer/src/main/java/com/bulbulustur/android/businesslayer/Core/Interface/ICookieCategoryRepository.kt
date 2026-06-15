package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CookieCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CookieCategoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CookieCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICookieCategoryRepository {

    @GET("api/CookieCategory/GetCookieCategoryListAsync")
    suspend fun GetCookieCategoryListAsync():
            Result<List<CookieCategoryDTO>>

    @GET("api/CookieCategory/GetCookieCategoryByIdAsync")
    suspend fun GetCookieCategoryByIdAsync(
        @Query("cookieCategoryId")
        cookieCategoryId: Int
    ): Result<CookieCategoryUpdateModel?>

    @GET("api/CookieCategory/GetCookieCategoryByIdExtendedAsync")
    suspend fun GetCookieCategoryByIdExtendedAsync(
        @Query("cookieCategoryId")
        cookieCategoryId: Int
    ): Result<CookieCategoryDTO?>

    @POST("api/CookieCategory/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CookieCategoryInsertModel
    ): Result<Unit>

    @POST("api/CookieCategory/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CookieCategoryUpdateModel
    ): Result<Unit>

    @POST("api/CookieCategory/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("cookieCategoryId")
        cookieCategoryId: Int
    ): Result<Unit>
}
