package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescOrderStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescOrderStatusRepository {

    @GET("api/SystemDescOrderStatus/GetSystemDescOrderStatusListAsync")
    suspend fun GetSystemDescOrderStatusListAsync():
            Result<List<SystemDescOrderStatusDTO>>

    @GET("api/SystemDescOrderStatus/GetSystemDescOrderStatusByIdAsync")
    suspend fun GetSystemDescOrderStatusByIdAsync(
        @Query("systemDescOrderStatusId")
        systemDescOrderStatusId: Int
    ): Result<SystemDescOrderStatusUpdateModel?>

    @GET("api/SystemDescOrderStatus/GetSystemDescOrderStatusByIdExtendedAsync")
    suspend fun GetSystemDescOrderStatusByIdExtendedAsync(
        @Query("systemDescOrderStatusId")
        systemDescOrderStatusId: Int
    ): Result<SystemDescOrderStatusDTO?>

    @POST("api/SystemDescOrderStatus/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescOrderStatusInsertModel
    ): Result<Unit>

    @POST("api/SystemDescOrderStatus/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescOrderStatusUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescOrderStatus/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescOrderStatusId")
        systemDescOrderStatusId: Int
    ): Result<Unit>
}
