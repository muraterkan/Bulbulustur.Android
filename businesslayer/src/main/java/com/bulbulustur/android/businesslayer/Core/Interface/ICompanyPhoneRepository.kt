package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyPhoneDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyPhoneInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyPhoneUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyPhoneRepository {

    @GET("api/CompanyPhone/GetCompanyPhoneListAsync")
    suspend fun GetCompanyPhoneListAsync():
            Result<List<CompanyPhoneDTO>>

    @GET("api/CompanyPhone/GetCompanyPhoneByIdAsync")
    suspend fun GetCompanyPhoneByIdAsync(
        @Query("companyPhoneId")
        companyPhoneId: Int
    ): Result<CompanyPhoneUpdateModel?>

    @GET("api/CompanyPhone/GetCompanyPhoneByIdExtendedAsync")
    suspend fun GetCompanyPhoneByIdExtendedAsync(
        @Query("companyPhoneId")
        companyPhoneId: Int
    ): Result<CompanyPhoneDTO?>

    @POST("api/CompanyPhone/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyPhoneInsertModel
    ): Result<Unit>

    @POST("api/CompanyPhone/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyPhoneUpdateModel
    ): Result<Unit>

    @POST("api/CompanyPhone/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyPhoneId")
        companyPhoneId: Int
    ): Result<Unit>
}
