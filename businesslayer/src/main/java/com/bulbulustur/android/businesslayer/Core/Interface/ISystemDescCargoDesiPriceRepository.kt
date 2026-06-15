package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoDesiPriceDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCargoDesiPriceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoDesiPriceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescCargoDesiPriceRepository {

    @GET("api/SystemDescCargoDesiPrice/GetSystemDescCargoDesiPriceListAsync")
    suspend fun GetSystemDescCargoDesiPriceListAsync():
            Result<List<SystemDescCargoDesiPriceDTO>>

    @GET("api/SystemDescCargoDesiPrice/GetSystemDescCargoDesiPriceByIdAsync")
    suspend fun GetSystemDescCargoDesiPriceByIdAsync(
        @Query("systemDescCargoDesiPriceId")
        systemDescCargoDesiPriceId: Int
    ): Result<SystemDescCargoDesiPriceUpdateModel?>

    @GET("api/SystemDescCargoDesiPrice/GetSystemDescCargoDesiPriceByIdExtendedAsync")
    suspend fun GetSystemDescCargoDesiPriceByIdExtendedAsync(
        @Query("systemDescCargoDesiPriceId")
        systemDescCargoDesiPriceId: Int
    ): Result<SystemDescCargoDesiPriceDTO?>

    @POST("api/SystemDescCargoDesiPrice/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescCargoDesiPriceInsertModel
    ): Result<Unit>

    @POST("api/SystemDescCargoDesiPrice/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescCargoDesiPriceUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescCargoDesiPrice/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescCargoDesiPriceId")
        systemDescCargoDesiPriceId: Int
    ): Result<Unit>
}
