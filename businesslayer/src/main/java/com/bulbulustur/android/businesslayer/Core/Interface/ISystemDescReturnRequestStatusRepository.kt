package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReturnRequestStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescReturnRequestStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescReturnRequestStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescReturnRequestStatusRepository {

    @GET("api/SystemDescReturnRequestStatus/GetSystemDescReturnRequestStatusListAsync")
    suspend fun GetSystemDescReturnRequestStatusListAsync():
            Result<List<SystemDescReturnRequestStatusDTO>>

    @GET("api/SystemDescReturnRequestStatus/GetSystemDescReturnRequestStatusByIdAsync")
    suspend fun GetSystemDescReturnRequestStatusByIdAsync(
        @Query("systemDescReturnRequestStatusId")
        systemDescReturnRequestStatusId: Int
    ): Result<SystemDescReturnRequestStatusUpdateModel?>

    @GET("api/SystemDescReturnRequestStatus/GetSystemDescReturnRequestStatusByIdExtendedAsync")
    suspend fun GetSystemDescReturnRequestStatusByIdExtendedAsync(
        @Query("systemDescReturnRequestStatusId")
        systemDescReturnRequestStatusId: Int
    ): Result<SystemDescReturnRequestStatusDTO?>

    @POST("api/SystemDescReturnRequestStatus/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescReturnRequestStatusInsertModel
    ): Result<Unit>

    @POST("api/SystemDescReturnRequestStatus/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescReturnRequestStatusUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescReturnRequestStatus/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescReturnRequestStatusId")
        systemDescReturnRequestStatusId: Int
    ): Result<Unit>
}
