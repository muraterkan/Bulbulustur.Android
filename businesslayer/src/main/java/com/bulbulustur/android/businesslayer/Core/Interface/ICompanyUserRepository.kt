package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyUserDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyUserInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUserUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyUserRepository {

    @GET("api/CompanyUser/GetCompanyUserListAsync")
    suspend fun GetCompanyUserListAsync():
            Result<List<CompanyUserDTO>>

    @GET("api/CompanyUser/GetCompanyUserByIdAsync")
    suspend fun GetCompanyUserByIdAsync(
        @Query("companyUserId")
        companyUserId: Int
    ): Result<CompanyUserUpdateModel?>

    @GET("api/CompanyUser/GetCompanyUserByIdExtendedAsync")
    suspend fun GetCompanyUserByIdExtendedAsync(
        @Query("companyUserId")
        companyUserId: Int
    ): Result<CompanyUserDTO?>

    @POST("api/CompanyUser/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyUserInsertModel
    ): Result<Unit>

    @POST("api/CompanyUser/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyUserUpdateModel
    ): Result<Unit>

    @POST("api/CompanyUser/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyUserId")
        companyUserId: Int
    ): Result<Unit>
}
