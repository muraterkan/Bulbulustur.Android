package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReturnRequestReasonDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescReturnRequestReasonInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescReturnRequestReasonUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescReturnRequestReasonRepository {

    @GET("api/SystemDescReturnRequestReason/GetSystemDescReturnRequestReasonListAsync")
    suspend fun GetSystemDescReturnRequestReasonListAsync():
            Result<List<SystemDescReturnRequestReasonDTO>>

    @GET("api/SystemDescReturnRequestReason/GetSystemDescReturnRequestReasonByIdAsync")
    suspend fun GetSystemDescReturnRequestReasonByIdAsync(
        @Query("systemDescReturnRequestReasonId")
        systemDescReturnRequestReasonId: Int
    ): Result<SystemDescReturnRequestReasonUpdateModel?>

    @GET("api/SystemDescReturnRequestReason/GetSystemDescReturnRequestReasonByIdExtendedAsync")
    suspend fun GetSystemDescReturnRequestReasonByIdExtendedAsync(
        @Query("systemDescReturnRequestReasonId")
        systemDescReturnRequestReasonId: Int
    ): Result<SystemDescReturnRequestReasonDTO?>

    @POST("api/SystemDescReturnRequestReason/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescReturnRequestReasonInsertModel
    ): Result<Unit>

    @POST("api/SystemDescReturnRequestReason/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescReturnRequestReasonUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescReturnRequestReason/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescReturnRequestReasonId")
        systemDescReturnRequestReasonId: Int
    ): Result<Unit>
}
