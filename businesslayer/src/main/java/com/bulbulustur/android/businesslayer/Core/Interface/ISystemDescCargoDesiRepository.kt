package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoDesiDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCargoDesiInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoDesiUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescCargoDesiRepository {

    @GET("api/SystemDescCargoDesi/GetSystemDescCargoDesiListAsync")
    suspend fun GetSystemDescCargoDesiListAsync():
            Result<List<SystemDescCargoDesiDTO>>

    @GET("api/SystemDescCargoDesi/GetSystemDescCargoDesiByIdAsync")
    suspend fun GetSystemDescCargoDesiByIdAsync(
        @Query("systemDescCargoDesiId")
        systemDescCargoDesiId: Int
    ): Result<SystemDescCargoDesiUpdateModel?>

    @GET("api/SystemDescCargoDesi/GetSystemDescCargoDesiByIdExtendedAsync")
    suspend fun GetSystemDescCargoDesiByIdExtendedAsync(
        @Query("systemDescCargoDesiId")
        systemDescCargoDesiId: Int
    ): Result<SystemDescCargoDesiDTO?>

    @POST("api/SystemDescCargoDesi/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescCargoDesiInsertModel
    ): Result<Unit>

    @POST("api/SystemDescCargoDesi/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescCargoDesiUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescCargoDesi/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescCargoDesiId")
        systemDescCargoDesiId: Int
    ): Result<Unit>
}
