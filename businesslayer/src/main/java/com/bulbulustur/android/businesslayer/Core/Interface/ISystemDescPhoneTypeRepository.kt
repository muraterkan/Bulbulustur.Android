package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPhoneTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPhoneTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPhoneTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescPhoneTypeRepository {

    @GET("api/SystemDescPhoneType/GetSystemDescPhoneTypeListAsync")
    suspend fun GetSystemDescPhoneTypeListAsync():
            Result<List<SystemDescPhoneTypeDTO>>

    @GET("api/SystemDescPhoneType/GetSystemDescPhoneTypeByIdAsync")
    suspend fun GetSystemDescPhoneTypeByIdAsync(
        @Query("systemDescPhoneTypeId")
        systemDescPhoneTypeId: Int
    ): Result<SystemDescPhoneTypeUpdateModel?>

    @GET("api/SystemDescPhoneType/GetSystemDescPhoneTypeByIdExtendedAsync")
    suspend fun GetSystemDescPhoneTypeByIdExtendedAsync(
        @Query("systemDescPhoneTypeId")
        systemDescPhoneTypeId: Int
    ): Result<SystemDescPhoneTypeDTO?>

    @POST("api/SystemDescPhoneType/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescPhoneTypeInsertModel
    ): Result<Unit>

    @POST("api/SystemDescPhoneType/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescPhoneTypeUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescPhoneType/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescPhoneTypeId")
        systemDescPhoneTypeId: Int
    ): Result<Unit>
}
