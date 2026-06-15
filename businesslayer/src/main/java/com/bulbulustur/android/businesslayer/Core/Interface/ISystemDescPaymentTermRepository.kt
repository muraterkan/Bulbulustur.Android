package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPaymentTermDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPaymentTermInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPaymentTermUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescPaymentTermRepository {

    @GET("api/SystemDescPaymentTerm/GetSystemDescPaymentTermListAsync")
    suspend fun GetSystemDescPaymentTermListAsync():
            Result<List<SystemDescPaymentTermDTO>>

    @GET("api/SystemDescPaymentTerm/GetSystemDescPaymentTermByIdAsync")
    suspend fun GetSystemDescPaymentTermByIdAsync(
        @Query("systemDescPaymentTermId")
        systemDescPaymentTermId: Int
    ): Result<SystemDescPaymentTermUpdateModel?>

    @GET("api/SystemDescPaymentTerm/GetSystemDescPaymentTermByIdExtendedAsync")
    suspend fun GetSystemDescPaymentTermByIdExtendedAsync(
        @Query("systemDescPaymentTermId")
        systemDescPaymentTermId: Int
    ): Result<SystemDescPaymentTermDTO?>

    @POST("api/SystemDescPaymentTerm/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescPaymentTermInsertModel
    ): Result<Unit>

    @POST("api/SystemDescPaymentTerm/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescPaymentTermUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescPaymentTerm/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescPaymentTermId")
        systemDescPaymentTermId: Int
    ): Result<Unit>
}
