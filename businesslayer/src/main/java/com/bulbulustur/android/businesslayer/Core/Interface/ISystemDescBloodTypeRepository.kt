package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBloodTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescBloodTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescBloodTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescBloodTypeRepository {

    @GET("api/SystemDescBloodType/GetSystemDescBloodTypeListAsync")
    suspend fun GetSystemDescBloodTypeListAsync():
            Result<List<SystemDescBloodTypeDTO>>

    @GET("api/SystemDescBloodType/GetSystemDescBloodTypeByIdAsync")
    suspend fun GetSystemDescBloodTypeByIdAsync(
        @Query("systemDescBloodTypeId")
        systemDescBloodTypeId: Int
    ): Result<SystemDescBloodTypeUpdateModel?>

    @GET("api/SystemDescBloodType/GetSystemDescBloodTypeByIdExtendedAsync")
    suspend fun GetSystemDescBloodTypeByIdExtendedAsync(
        @Query("systemDescBloodTypeId")
        systemDescBloodTypeId: Int
    ): Result<SystemDescBloodTypeDTO?>

    @POST("api/SystemDescBloodType/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescBloodTypeInsertModel
    ): Result<Unit>

    @POST("api/SystemDescBloodType/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescBloodTypeUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescBloodType/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescBloodTypeId")
        systemDescBloodTypeId: Int
    ): Result<Unit>
}
