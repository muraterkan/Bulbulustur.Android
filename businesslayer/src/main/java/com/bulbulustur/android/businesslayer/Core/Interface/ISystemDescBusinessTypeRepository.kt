package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBusinessTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescBusinessTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescBusinessTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescBusinessTypeRepository {

    @GET("api/SystemDescBusinessType/GetSystemDescBusinessTypeListAsync")
    suspend fun GetSystemDescBusinessTypeListAsync():
            Result<List<SystemDescBusinessTypeDTO>>

    @GET("api/SystemDescBusinessType/GetSystemDescBusinessTypeByIdAsync")
    suspend fun GetSystemDescBusinessTypeByIdAsync(
        @Query("systemDescBusinessTypeId")
        systemDescBusinessTypeId: Int
    ): Result<SystemDescBusinessTypeUpdateModel?>

    @GET("api/SystemDescBusinessType/GetSystemDescBusinessTypeByIdExtendedAsync")
    suspend fun GetSystemDescBusinessTypeByIdExtendedAsync(
        @Query("systemDescBusinessTypeId")
        systemDescBusinessTypeId: Int
    ): Result<SystemDescBusinessTypeDTO?>

    @POST("api/SystemDescBusinessType/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescBusinessTypeInsertModel
    ): Result<Unit>

    @POST("api/SystemDescBusinessType/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescBusinessTypeUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescBusinessType/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescBusinessTypeId")
        systemDescBusinessTypeId: Int
    ): Result<Unit>
}
