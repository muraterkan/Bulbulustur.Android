package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderStoreStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescOrderStoreStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderStoreStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescOrderStoreStatusRepository {

    @GET("api/SystemDescOrderStoreStatus/GetSystemDescOrderStoreStatusListAsync")
    suspend fun GetSystemDescOrderStoreStatusListAsync():
            Result<List<SystemDescOrderStoreStatusDTO>>

    @GET("api/SystemDescOrderStoreStatus/GetSystemDescOrderStoreStatusByIdAsync")
    suspend fun GetSystemDescOrderStoreStatusByIdAsync(
        @Query("systemDescOrderStoreStatusId")
        systemDescOrderStoreStatusId: Int
    ): Result<SystemDescOrderStoreStatusUpdateModel?>

    @GET("api/SystemDescOrderStoreStatus/GetSystemDescOrderStoreStatusByIdExtendedAsync")
    suspend fun GetSystemDescOrderStoreStatusByIdExtendedAsync(
        @Query("systemDescOrderStoreStatusId")
        systemDescOrderStoreStatusId: Int
    ): Result<SystemDescOrderStoreStatusDTO?>

    @POST("api/SystemDescOrderStoreStatus/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescOrderStoreStatusInsertModel
    ): Result<Unit>

    @POST("api/SystemDescOrderStoreStatus/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescOrderStoreStatusUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescOrderStoreStatus/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescOrderStoreStatusId")
        systemDescOrderStoreStatusId: Int
    ): Result<Unit>
}
