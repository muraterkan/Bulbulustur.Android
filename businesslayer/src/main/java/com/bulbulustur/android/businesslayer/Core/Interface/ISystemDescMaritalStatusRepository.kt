package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMaritalStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescMaritalStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMaritalStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescMaritalStatusRepository {

    @GET("api/SystemDescMaritalStatus/GetSystemDescMaritalStatusListAsync")
    suspend fun GetSystemDescMaritalStatusListAsync():
            Result<List<SystemDescMaritalStatusDTO>>

    @GET("api/SystemDescMaritalStatus/GetSystemDescMaritalStatusByIdAsync")
    suspend fun GetSystemDescMaritalStatusByIdAsync(
        @Query("systemDescMaritalStatusId")
        systemDescMaritalStatusId: Int
    ): Result<SystemDescMaritalStatusUpdateModel?>

    @GET("api/SystemDescMaritalStatus/GetSystemDescMaritalStatusByIdExtendedAsync")
    suspend fun GetSystemDescMaritalStatusByIdExtendedAsync(
        @Query("systemDescMaritalStatusId")
        systemDescMaritalStatusId: Int
    ): Result<SystemDescMaritalStatusDTO?>

    @POST("api/SystemDescMaritalStatus/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescMaritalStatusInsertModel
    ): Result<Unit>

    @POST("api/SystemDescMaritalStatus/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescMaritalStatusUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescMaritalStatus/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescMaritalStatusId")
        systemDescMaritalStatusId: Int
    ): Result<Unit>
}
