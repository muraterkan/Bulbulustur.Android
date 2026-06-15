package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyCapabilityDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyCapabilityInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyCapabilityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyCapabilityRepository {

    @GET("api/CompanyCapability/GetCompanyCapabilityListAsync")
    suspend fun GetCompanyCapabilityListAsync():
            Result<List<CompanyCapabilityDTO>>

    @GET("api/CompanyCapability/GetCompanyCapabilityByIdAsync")
    suspend fun GetCompanyCapabilityByIdAsync(
        @Query("companyCapabilityId")
        companyCapabilityId: Int
    ): Result<CompanyCapabilityUpdateModel?>

    @GET("api/CompanyCapability/GetCompanyCapabilityByIdExtendedAsync")
    suspend fun GetCompanyCapabilityByIdExtendedAsync(
        @Query("companyCapabilityId")
        companyCapabilityId: Int
    ): Result<CompanyCapabilityDTO?>

    @POST("api/CompanyCapability/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyCapabilityInsertModel
    ): Result<Unit>

    @POST("api/CompanyCapability/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyCapabilityUpdateModel
    ): Result<Unit>

    @POST("api/CompanyCapability/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyCapabilityId")
        companyCapabilityId: Int
    ): Result<Unit>
}
