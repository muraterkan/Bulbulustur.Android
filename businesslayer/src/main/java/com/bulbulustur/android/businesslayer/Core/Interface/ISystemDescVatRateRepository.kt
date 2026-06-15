package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescVatRateDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescVatRateInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescVatRateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescVatRateRepository {

    @GET("api/SystemDescVatRate/GetSystemDescVatRateListAsync")
    suspend fun GetSystemDescVatRateListAsync():
            Result<List<SystemDescVatRateDTO>>

    @GET("api/SystemDescVatRate/GetSystemDescVatRateByIdAsync")
    suspend fun GetSystemDescVatRateByIdAsync(
        @Query("systemDescVatRateId")
        systemDescVatRateId: Int
    ): Result<SystemDescVatRateUpdateModel?>

    @GET("api/SystemDescVatRate/GetSystemDescVatRateByIdExtendedAsync")
    suspend fun GetSystemDescVatRateByIdExtendedAsync(
        @Query("systemDescVatRateId")
        systemDescVatRateId: Int
    ): Result<SystemDescVatRateDTO?>

    @POST("api/SystemDescVatRate/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescVatRateInsertModel
    ): Result<Unit>

    @POST("api/SystemDescVatRate/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescVatRateUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescVatRate/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescVatRateId")
        systemDescVatRateId: Int
    ): Result<Unit>
}
