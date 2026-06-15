package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescColorDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescColorInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescColorUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescColorRepository {

    @GET("api/SystemDescColor/GetSystemDescColorListAsync")
    suspend fun GetSystemDescColorListAsync():
            Result<List<SystemDescColorDTO>>

    @GET("api/SystemDescColor/GetSystemDescColorByIdAsync")
    suspend fun GetSystemDescColorByIdAsync(
        @Query("systemDescColorId")
        systemDescColorId: Int
    ): Result<SystemDescColorUpdateModel?>

    @GET("api/SystemDescColor/GetSystemDescColorByIdExtendedAsync")
    suspend fun GetSystemDescColorByIdExtendedAsync(
        @Query("systemDescColorId")
        systemDescColorId: Int
    ): Result<SystemDescColorDTO?>

    @POST("api/SystemDescColor/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescColorInsertModel
    ): Result<Unit>

    @POST("api/SystemDescColor/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescColorUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescColor/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescColorId")
        systemDescColorId: Int
    ): Result<Unit>
}
