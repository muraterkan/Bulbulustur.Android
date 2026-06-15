package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyBranchDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyBranchInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyBranchUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyBranchRepository {

    @GET("api/CompanyBranch/GetCompanyBranchListAsync")
    suspend fun GetCompanyBranchListAsync():
            Result<List<CompanyBranchDTO>>

    @GET("api/CompanyBranch/GetCompanyBranchByIdAsync")
    suspend fun GetCompanyBranchByIdAsync(
        @Query("companyBranchId")
        companyBranchId: Int
    ): Result<CompanyBranchUpdateModel?>

    @GET("api/CompanyBranch/GetCompanyBranchByIdExtendedAsync")
    suspend fun GetCompanyBranchByIdExtendedAsync(
        @Query("companyBranchId")
        companyBranchId: Int
    ): Result<CompanyBranchDTO?>

    @POST("api/CompanyBranch/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyBranchInsertModel
    ): Result<Unit>

    @POST("api/CompanyBranch/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyBranchUpdateModel
    ): Result<Unit>

    @POST("api/CompanyBranch/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyBranchId")
        companyBranchId: Int
    ): Result<Unit>
}
