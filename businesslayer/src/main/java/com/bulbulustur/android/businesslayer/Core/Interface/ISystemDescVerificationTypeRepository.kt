package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescVerificationTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescVerificationTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescVerificationTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescVerificationTypeRepository {

    @GET("api/SystemDescVerificationType/GetSystemDescVerificationTypeListAsync")
    suspend fun GetSystemDescVerificationTypeListAsync():
            Result<List<SystemDescVerificationTypeDTO>>

    @GET("api/SystemDescVerificationType/GetSystemDescVerificationTypeByIdAsync")
    suspend fun GetSystemDescVerificationTypeByIdAsync(
        @Query("systemDescVerificationTypeId")
        systemDescVerificationTypeId: Int
    ): Result<SystemDescVerificationTypeUpdateModel?>

    @GET("api/SystemDescVerificationType/GetSystemDescVerificationTypeByIdExtendedAsync")
    suspend fun GetSystemDescVerificationTypeByIdExtendedAsync(
        @Query("systemDescVerificationTypeId")
        systemDescVerificationTypeId: Int
    ): Result<SystemDescVerificationTypeDTO?>

    @POST("api/SystemDescVerificationType/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescVerificationTypeInsertModel
    ): Result<Unit>

    @POST("api/SystemDescVerificationType/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescVerificationTypeUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescVerificationType/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescVerificationTypeId")
        systemDescVerificationTypeId: Int
    ): Result<Unit>
}
