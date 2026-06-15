package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAccountActivityTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescAccountActivityTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescAccountActivityTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescAccountActivityTypeRepository {

    @GET("api/SystemDescAccountActivityType/GetSystemDescAccountActivityTypeListAsync")
    suspend fun GetSystemDescAccountActivityTypeListAsync():
            Result<List<SystemDescAccountActivityTypeDTO>>

    @GET("api/SystemDescAccountActivityType/GetSystemDescAccountActivityTypeByIdAsync")
    suspend fun GetSystemDescAccountActivityTypeByIdAsync(
        @Query("systemDescAccountActivityTypeId")
        systemDescAccountActivityTypeId: Int
    ): Result<SystemDescAccountActivityTypeUpdateModel?>

    @GET("api/SystemDescAccountActivityType/GetSystemDescAccountActivityTypeByIdExtendedAsync")
    suspend fun GetSystemDescAccountActivityTypeByIdExtendedAsync(
        @Query("systemDescAccountActivityTypeId")
        systemDescAccountActivityTypeId: Int
    ): Result<SystemDescAccountActivityTypeDTO?>

    @POST("api/SystemDescAccountActivityType/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescAccountActivityTypeInsertModel
    ): Result<Unit>

    @POST("api/SystemDescAccountActivityType/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescAccountActivityTypeUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescAccountActivityType/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescAccountActivityTypeId")
        systemDescAccountActivityTypeId: Int
    ): Result<Unit>
}
