package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSizeTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescSizeTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescSizeTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescSizeTypeRepository {

    @GET("api/SystemDescSizeType/GetSystemDescSizeTypeListAsync")
    suspend fun GetSystemDescSizeTypeListAsync():
            Result<List<SystemDescSizeTypeDTO>>

    @GET("api/SystemDescSizeType/GetSystemDescSizeTypeByIdAsync")
    suspend fun GetSystemDescSizeTypeByIdAsync(
        @Query("systemDescSizeTypeId")
        systemDescSizeTypeId: Int
    ): Result<SystemDescSizeTypeUpdateModel?>

    @GET("api/SystemDescSizeType/GetSystemDescSizeTypeByIdExtendedAsync")
    suspend fun GetSystemDescSizeTypeByIdExtendedAsync(
        @Query("systemDescSizeTypeId")
        systemDescSizeTypeId: Int
    ): Result<SystemDescSizeTypeDTO?>

    @POST("api/SystemDescSizeType/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescSizeTypeInsertModel
    ): Result<Unit>

    @POST("api/SystemDescSizeType/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescSizeTypeUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescSizeType/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescSizeTypeId")
        systemDescSizeTypeId: Int
    ): Result<Unit>
}
