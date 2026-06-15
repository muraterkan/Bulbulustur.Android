package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCertificateTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCertificateTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCertificateTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescCertificateTypeRepository {

    @GET("api/SystemDescCertificateType/GetSystemDescCertificateTypeListAsync")
    suspend fun GetSystemDescCertificateTypeListAsync():
            Result<List<SystemDescCertificateTypeDTO>>

    @GET("api/SystemDescCertificateType/GetSystemDescCertificateTypeByIdAsync")
    suspend fun GetSystemDescCertificateTypeByIdAsync(
        @Query("systemDescCertificateTypeId")
        systemDescCertificateTypeId: Int
    ): Result<SystemDescCertificateTypeUpdateModel?>

    @GET("api/SystemDescCertificateType/GetSystemDescCertificateTypeByIdExtendedAsync")
    suspend fun GetSystemDescCertificateTypeByIdExtendedAsync(
        @Query("systemDescCertificateTypeId")
        systemDescCertificateTypeId: Int
    ): Result<SystemDescCertificateTypeDTO?>

    @POST("api/SystemDescCertificateType/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescCertificateTypeInsertModel
    ): Result<Unit>

    @POST("api/SystemDescCertificateType/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescCertificateTypeUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescCertificateType/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescCertificateTypeId")
        systemDescCertificateTypeId: Int
    ): Result<Unit>
}
