package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescNumberOfEmployeeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescNumberOfEmployeeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescNumberOfEmployeeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescNumberOfEmployeeRepository {

    @GET("api/SystemDescNumberOfEmployee/GetSystemDescNumberOfEmployeeListAsync")
    suspend fun GetSystemDescNumberOfEmployeeListAsync():
            Result<List<SystemDescNumberOfEmployeeDTO>>

    @GET("api/SystemDescNumberOfEmployee/GetSystemDescNumberOfEmployeeByIdAsync")
    suspend fun GetSystemDescNumberOfEmployeeByIdAsync(
        @Query("systemDescNumberOfEmployeeId")
        systemDescNumberOfEmployeeId: Int
    ): Result<SystemDescNumberOfEmployeeUpdateModel?>

    @GET("api/SystemDescNumberOfEmployee/GetSystemDescNumberOfEmployeeByIdExtendedAsync")
    suspend fun GetSystemDescNumberOfEmployeeByIdExtendedAsync(
        @Query("systemDescNumberOfEmployeeId")
        systemDescNumberOfEmployeeId: Int
    ): Result<SystemDescNumberOfEmployeeDTO?>

    @POST("api/SystemDescNumberOfEmployee/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescNumberOfEmployeeInsertModel
    ): Result<Unit>

    @POST("api/SystemDescNumberOfEmployee/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescNumberOfEmployeeUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescNumberOfEmployee/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescNumberOfEmployeeId")
        systemDescNumberOfEmployeeId: Int
    ): Result<Unit>
}
