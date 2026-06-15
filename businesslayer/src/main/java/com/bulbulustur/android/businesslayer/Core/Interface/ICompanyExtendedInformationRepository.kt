package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyExtendedInformationDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyExtendedInformationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyExtendedInformationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyExtendedInformationRepository {

    @GET("api/CompanyExtendedInformation/GetCompanyExtendedInformationListAsync")
    suspend fun GetCompanyExtendedInformationListAsync():
            Result<List<CompanyExtendedInformationDTO>>

    @GET("api/CompanyExtendedInformation/GetCompanyExtendedInformationByIdAsync")
    suspend fun GetCompanyExtendedInformationByIdAsync(
        @Query("companyExtendedInformationId")
        companyExtendedInformationId: Int
    ): Result<CompanyExtendedInformationUpdateModel?>

    @GET("api/CompanyExtendedInformation/GetCompanyExtendedInformationByIdExtendedAsync")
    suspend fun GetCompanyExtendedInformationByIdExtendedAsync(
        @Query("companyExtendedInformationId")
        companyExtendedInformationId: Int
    ): Result<CompanyExtendedInformationDTO?>

    @POST("api/CompanyExtendedInformation/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyExtendedInformationInsertModel
    ): Result<Unit>

    @POST("api/CompanyExtendedInformation/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyExtendedInformationUpdateModel
    ): Result<Unit>

    @POST("api/CompanyExtendedInformation/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyExtendedInformationId")
        companyExtendedInformationId: Int
    ): Result<Unit>
}
