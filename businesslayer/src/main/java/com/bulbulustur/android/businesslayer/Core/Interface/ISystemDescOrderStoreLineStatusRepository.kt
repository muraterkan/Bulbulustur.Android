package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderStoreLineStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescOrderStoreLineStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderStoreLineStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescOrderStoreLineStatusRepository {

    @GET("api/SystemDescOrderStoreLineStatus/GetSystemDescOrderStoreLineStatusListAsync")
    suspend fun GetSystemDescOrderStoreLineStatusListAsync():
            Result<List<SystemDescOrderStoreLineStatusDTO>>

    @GET("api/SystemDescOrderStoreLineStatus/GetSystemDescOrderStoreLineStatusByIdAsync")
    suspend fun GetSystemDescOrderStoreLineStatusByIdAsync(
        @Query("systemDescOrderStoreLineStatusId")
        systemDescOrderStoreLineStatusId: Int
    ): Result<SystemDescOrderStoreLineStatusUpdateModel?>

    @GET("api/SystemDescOrderStoreLineStatus/GetSystemDescOrderStoreLineStatusByIdExtendedAsync")
    suspend fun GetSystemDescOrderStoreLineStatusByIdExtendedAsync(
        @Query("systemDescOrderStoreLineStatusId")
        systemDescOrderStoreLineStatusId: Int
    ): Result<SystemDescOrderStoreLineStatusDTO?>

    @POST("api/SystemDescOrderStoreLineStatus/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescOrderStoreLineStatusInsertModel
    ): Result<Unit>

    @POST("api/SystemDescOrderStoreLineStatus/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescOrderStoreLineStatusUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescOrderStoreLineStatus/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescOrderStoreLineStatusId")
        systemDescOrderStoreLineStatusId: Int
    ): Result<Unit>
}
