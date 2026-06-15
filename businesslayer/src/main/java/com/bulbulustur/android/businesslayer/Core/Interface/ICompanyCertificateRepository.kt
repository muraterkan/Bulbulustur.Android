package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyCertificateDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyCertificateInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyCertificateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyCertificateRepository {

    @GET("api/CompanyCertificate/GetCompanyCertificateListAsync")
    suspend fun GetCompanyCertificateListAsync():
            Result<List<CompanyCertificateDTO>>

    @GET("api/CompanyCertificate/GetCompanyCertificateByIdAsync")
    suspend fun GetCompanyCertificateByIdAsync(
        @Query("companyCertificateId")
        companyCertificateId: Int
    ): Result<CompanyCertificateUpdateModel?>

    @GET("api/CompanyCertificate/GetCompanyCertificateByIdExtendedAsync")
    suspend fun GetCompanyCertificateByIdExtendedAsync(
        @Query("companyCertificateId")
        companyCertificateId: Int
    ): Result<CompanyCertificateDTO?>

    @POST("api/CompanyCertificate/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyCertificateInsertModel
    ): Result<Unit>

    @POST("api/CompanyCertificate/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyCertificateUpdateModel
    ): Result<Unit>

    @POST("api/CompanyCertificate/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyCertificateId")
        companyCertificateId: Int
    ): Result<Unit>
}
