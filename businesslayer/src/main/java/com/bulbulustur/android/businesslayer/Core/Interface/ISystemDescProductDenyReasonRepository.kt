package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescProductDenyReasonDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescProductDenyReasonInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescProductDenyReasonUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescProductDenyReasonRepository {

    @GET("api/SystemDescProductDenyReason/GetSystemDescProductDenyReasonListAsync")
    suspend fun GetSystemDescProductDenyReasonListAsync():
            Result<List<SystemDescProductDenyReasonDTO>>

    @GET("api/SystemDescProductDenyReason/GetSystemDescProductDenyReasonByIdAsync")
    suspend fun GetSystemDescProductDenyReasonByIdAsync(
        @Query("systemDescProductDenyReasonId")
        systemDescProductDenyReasonId: Int
    ): Result<SystemDescProductDenyReasonUpdateModel?>

    @GET("api/SystemDescProductDenyReason/GetSystemDescProductDenyReasonByIdExtendedAsync")
    suspend fun GetSystemDescProductDenyReasonByIdExtendedAsync(
        @Query("systemDescProductDenyReasonId")
        systemDescProductDenyReasonId: Int
    ): Result<SystemDescProductDenyReasonDTO?>

    @POST("api/SystemDescProductDenyReason/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescProductDenyReasonInsertModel
    ): Result<Unit>

    @POST("api/SystemDescProductDenyReason/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescProductDenyReasonUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescProductDenyReason/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescProductDenyReasonId")
        systemDescProductDenyReasonId: Int
    ): Result<Unit>
}
