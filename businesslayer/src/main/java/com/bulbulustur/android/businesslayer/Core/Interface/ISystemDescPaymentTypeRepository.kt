package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPaymentTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPaymentTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPaymentTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescPaymentTypeRepository {

    @GET("api/SystemDescPaymentType/GetSystemDescPaymentTypeListAsync")
    suspend fun GetSystemDescPaymentTypeListAsync():
            Result<List<SystemDescPaymentTypeDTO>>

    @GET("api/SystemDescPaymentType/GetSystemDescPaymentTypeByIdAsync")
    suspend fun GetSystemDescPaymentTypeByIdAsync(
        @Query("systemDescPaymentTypeId")
        systemDescPaymentTypeId: Int
    ): Result<SystemDescPaymentTypeUpdateModel?>

    @GET("api/SystemDescPaymentType/GetSystemDescPaymentTypeByIdExtendedAsync")
    suspend fun GetSystemDescPaymentTypeByIdExtendedAsync(
        @Query("systemDescPaymentTypeId")
        systemDescPaymentTypeId: Int
    ): Result<SystemDescPaymentTypeDTO?>

    @POST("api/SystemDescPaymentType/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescPaymentTypeInsertModel
    ): Result<Unit>

    @POST("api/SystemDescPaymentType/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescPaymentTypeUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescPaymentType/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescPaymentTypeId")
        systemDescPaymentTypeId: Int
    ): Result<Unit>
}
