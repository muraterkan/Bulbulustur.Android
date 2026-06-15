package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCurrencyDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCurrencyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCurrencyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescCurrencyRepository {

    @GET("api/SystemDescCurrency/GetSystemDescCurrencyListAsync")
    suspend fun GetSystemDescCurrencyListAsync():
            Result<List<SystemDescCurrencyDTO>>

    @GET("api/SystemDescCurrency/GetSystemDescCurrencyByIdAsync")
    suspend fun GetSystemDescCurrencyByIdAsync(
        @Query("systemDescCurrencyId")
        systemDescCurrencyId: Int
    ): Result<SystemDescCurrencyUpdateModel?>

    @GET("api/SystemDescCurrency/GetSystemDescCurrencyByIdExtendedAsync")
    suspend fun GetSystemDescCurrencyByIdExtendedAsync(
        @Query("systemDescCurrencyId")
        systemDescCurrencyId: Int
    ): Result<SystemDescCurrencyDTO?>

    @POST("api/SystemDescCurrency/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescCurrencyInsertModel
    ): Result<Unit>

    @POST("api/SystemDescCurrency/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescCurrencyUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescCurrency/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescCurrencyId")
        systemDescCurrencyId: Int
    ): Result<Unit>
}
