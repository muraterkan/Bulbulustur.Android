package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyBankAccountDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyBankAccountInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyBankAccountUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyBankAccountRepository {

    @GET("api/CompanyBankAccount/GetCompanyBankAccountListAsync")
    suspend fun GetCompanyBankAccountListAsync():
            Result<List<CompanyBankAccountDTO>>

    @GET("api/CompanyBankAccount/GetCompanyBankAccountByIdAsync")
    suspend fun GetCompanyBankAccountByIdAsync(
        @Query("companyBankAccountId")
        companyBankAccountId: Int
    ): Result<CompanyBankAccountUpdateModel?>

    @GET("api/CompanyBankAccount/GetCompanyBankAccountByIdExtendedAsync")
    suspend fun GetCompanyBankAccountByIdExtendedAsync(
        @Query("companyBankAccountId")
        companyBankAccountId: Int
    ): Result<CompanyBankAccountDTO?>

    @POST("api/CompanyBankAccount/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyBankAccountInsertModel
    ): Result<Unit>

    @POST("api/CompanyBankAccount/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyBankAccountUpdateModel
    ): Result<Unit>

    @POST("api/CompanyBankAccount/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyBankAccountId")
        companyBankAccountId: Int
    ): Result<Unit>
}
