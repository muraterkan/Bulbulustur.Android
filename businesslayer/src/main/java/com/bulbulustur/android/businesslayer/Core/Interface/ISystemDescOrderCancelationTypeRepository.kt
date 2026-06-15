package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderCancelationTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescOrderCancelationTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderCancelationTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescOrderCancelationTypeRepository {

    @GET("api/SystemDescOrderCancelationType/GetSystemDescOrderCancelationTypeListAsync")
    suspend fun GetSystemDescOrderCancelationTypeListAsync():
            Result<List<SystemDescOrderCancelationTypeDTO>>

    @GET("api/SystemDescOrderCancelationType/GetSystemDescOrderCancelationTypeByIdAsync")
    suspend fun GetSystemDescOrderCancelationTypeByIdAsync(
        @Query("systemDescOrderCancelationTypeId")
        systemDescOrderCancelationTypeId: Int
    ): Result<SystemDescOrderCancelationTypeUpdateModel?>

    @GET("api/SystemDescOrderCancelationType/GetSystemDescOrderCancelationTypeByIdExtendedAsync")
    suspend fun GetSystemDescOrderCancelationTypeByIdExtendedAsync(
        @Query("systemDescOrderCancelationTypeId")
        systemDescOrderCancelationTypeId: Int
    ): Result<SystemDescOrderCancelationTypeDTO?>

    @POST("api/SystemDescOrderCancelationType/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescOrderCancelationTypeInsertModel
    ): Result<Unit>

    @POST("api/SystemDescOrderCancelationType/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescOrderCancelationTypeUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescOrderCancelationType/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescOrderCancelationTypeId")
        systemDescOrderCancelationTypeId: Int
    ): Result<Unit>
}
