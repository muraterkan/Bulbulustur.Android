package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAccountDisableReasonDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescAccountDisableReasonInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescAccountDisableReasonUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescAccountDisableReasonRepository {

    @GET("api/SystemDescAccountDisableReason/GetSystemDescAccountDisableReasonListAsync")
    suspend fun GetSystemDescAccountDisableReasonListAsync():
            Result<List<SystemDescAccountDisableReasonDTO>>

    @GET("api/SystemDescAccountDisableReason/GetSystemDescAccountDisableReasonByIdAsync")
    suspend fun GetSystemDescAccountDisableReasonByIdAsync(
        @Query("systemDescAccountDisableReasonId")
        systemDescAccountDisableReasonId: Int
    ): Result<SystemDescAccountDisableReasonUpdateModel?>

    @GET("api/SystemDescAccountDisableReason/GetSystemDescAccountDisableReasonByIdExtendedAsync")
    suspend fun GetSystemDescAccountDisableReasonByIdExtendedAsync(
        @Query("systemDescAccountDisableReasonId")
        systemDescAccountDisableReasonId: Int
    ): Result<SystemDescAccountDisableReasonDTO?>

    @POST("api/SystemDescAccountDisableReason/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescAccountDisableReasonInsertModel
    ): Result<Unit>

    @POST("api/SystemDescAccountDisableReason/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescAccountDisableReasonUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescAccountDisableReason/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescAccountDisableReasonId")
        systemDescAccountDisableReasonId: Int
    ): Result<Unit>
}
