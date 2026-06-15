package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyVerificationDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyVerificationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyVerificationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyVerificationRepository {

    @GET("api/CompanyVerification/GetCompanyVerificationListAsync")
    suspend fun GetCompanyVerificationListAsync():
            Result<List<CompanyVerificationDTO>>

    @GET("api/CompanyVerification/GetCompanyVerificationByIdAsync")
    suspend fun GetCompanyVerificationByIdAsync(
        @Query("companyVerificationId")
        companyVerificationId: Int
    ): Result<CompanyVerificationUpdateModel?>

    @GET("api/CompanyVerification/GetCompanyVerificationByIdExtendedAsync")
    suspend fun GetCompanyVerificationByIdExtendedAsync(
        @Query("companyVerificationId")
        companyVerificationId: Int
    ): Result<CompanyVerificationDTO?>

    @POST("api/CompanyVerification/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyVerificationInsertModel
    ): Result<Unit>

    @POST("api/CompanyVerification/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyVerificationUpdateModel
    ): Result<Unit>

    @POST("api/CompanyVerification/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyVerificationId")
        companyVerificationId: Int
    ): Result<Unit>
}
