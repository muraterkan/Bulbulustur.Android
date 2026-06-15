package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyRepository {

    @GET("api/Company/GetCompanyListAsync")
    suspend fun GetCompanyListAsync():
            Result<List<CompanyDTO>>

    @GET("api/Company/GetCompanyByIdAsync")
    suspend fun GetCompanyByIdAsync(
        @Query("companyId")
        companyId: Int
    ): Result<CompanyUpdateModel?>

    @GET("api/Company/GetCompanyByIdExtendedAsync")
    suspend fun GetCompanyByIdExtendedAsync(
        @Query("companyId")
        companyId: Int
    ): Result<CompanyDTO?>

    @POST("api/Company/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyInsertModel
    ): Result<Unit>

    @POST("api/Company/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyUpdateModel
    ): Result<Unit>

    @POST("api/Company/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyId")
        companyId: Int
    ): Result<Unit>
}
