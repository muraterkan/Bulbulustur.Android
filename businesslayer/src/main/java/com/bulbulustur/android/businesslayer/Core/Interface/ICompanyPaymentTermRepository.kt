package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyPaymentTermDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyPaymentTermInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyPaymentTermUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyPaymentTermRepository {

    @GET("api/CompanyPaymentTerm/GetCompanyPaymentTermListAsync")
    suspend fun GetCompanyPaymentTermListAsync():
            Result<List<CompanyPaymentTermDTO>>

    @GET("api/CompanyPaymentTerm/GetCompanyPaymentTermByIdAsync")
    suspend fun GetCompanyPaymentTermByIdAsync(
        @Query("companyPaymentTermId")
        companyPaymentTermId: Int
    ): Result<CompanyPaymentTermUpdateModel?>

    @GET("api/CompanyPaymentTerm/GetCompanyPaymentTermByIdExtendedAsync")
    suspend fun GetCompanyPaymentTermByIdExtendedAsync(
        @Query("companyPaymentTermId")
        companyPaymentTermId: Int
    ): Result<CompanyPaymentTermDTO?>

    @POST("api/CompanyPaymentTerm/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyPaymentTermInsertModel
    ): Result<Unit>

    @POST("api/CompanyPaymentTerm/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyPaymentTermUpdateModel
    ): Result<Unit>

    @POST("api/CompanyPaymentTerm/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyPaymentTermId")
        companyPaymentTermId: Int
    ): Result<Unit>
}
