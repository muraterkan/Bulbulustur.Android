package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescUnitDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescUnitInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescUnitUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescUnitRepository {

    @GET("api/SystemDescUnit/GetSystemDescUnitListAsync")
    suspend fun GetSystemDescUnitListAsync():
            Result<List<SystemDescUnitDTO>>

    @GET("api/SystemDescUnit/GetSystemDescUnitByIdAsync")
    suspend fun GetSystemDescUnitByIdAsync(
        @Query("systemDescUnitId")
        systemDescUnitId: Int
    ): Result<SystemDescUnitUpdateModel?>

    @GET("api/SystemDescUnit/GetSystemDescUnitByIdExtendedAsync")
    suspend fun GetSystemDescUnitByIdExtendedAsync(
        @Query("systemDescUnitId")
        systemDescUnitId: Int
    ): Result<SystemDescUnitDTO?>

    @POST("api/SystemDescUnit/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescUnitInsertModel
    ): Result<Unit>

    @POST("api/SystemDescUnit/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescUnitUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescUnit/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescUnitId")
        systemDescUnitId: Int
    ): Result<Unit>
}
