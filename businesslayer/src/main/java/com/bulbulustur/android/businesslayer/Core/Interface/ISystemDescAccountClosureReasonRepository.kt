package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAccountClosureReasonDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescAccountClosureReasonInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescAccountClosureReasonUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescAccountClosureReasonRepository {

    @GET("api/SystemDescAccountClosureReason/GetSystemDescAccountClosureReasonListAsync")
    suspend fun GetSystemDescAccountClosureReasonListAsync():
            Result<List<SystemDescAccountClosureReasonDTO>>

    @GET("api/SystemDescAccountClosureReason/GetSystemDescAccountClosureReasonByIdAsync")
    suspend fun GetSystemDescAccountClosureReasonByIdAsync(
        @Query("systemDescAccountClosureReasonId")
        systemDescAccountClosureReasonId: Int
    ): Result<SystemDescAccountClosureReasonUpdateModel?>

    @GET("api/SystemDescAccountClosureReason/GetSystemDescAccountClosureReasonByIdExtendedAsync")
    suspend fun GetSystemDescAccountClosureReasonByIdExtendedAsync(
        @Query("systemDescAccountClosureReasonId")
        systemDescAccountClosureReasonId: Int
    ): Result<SystemDescAccountClosureReasonDTO?>

    @POST("api/SystemDescAccountClosureReason/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescAccountClosureReasonInsertModel
    ): Result<Unit>

    @POST("api/SystemDescAccountClosureReason/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescAccountClosureReasonUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescAccountClosureReason/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescAccountClosureReasonId")
        systemDescAccountClosureReasonId: Int
    ): Result<Unit>
}
