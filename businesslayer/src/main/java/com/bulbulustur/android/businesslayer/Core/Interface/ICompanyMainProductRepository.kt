package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyMainProductDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyMainProductInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyMainProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyMainProductRepository {

    @GET("api/CompanyMainProduct/GetCompanyMainProductListAsync")
    suspend fun GetCompanyMainProductListAsync():
            Result<List<CompanyMainProductDTO>>

    @GET("api/CompanyMainProduct/GetCompanyMainProductByIdAsync")
    suspend fun GetCompanyMainProductByIdAsync(
        @Query("companyMainProductId")
        companyMainProductId: Int
    ): Result<CompanyMainProductUpdateModel?>

    @GET("api/CompanyMainProduct/GetCompanyMainProductByIdExtendedAsync")
    suspend fun GetCompanyMainProductByIdExtendedAsync(
        @Query("companyMainProductId")
        companyMainProductId: Int
    ): Result<CompanyMainProductDTO?>

    @POST("api/CompanyMainProduct/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyMainProductInsertModel
    ): Result<Unit>

    @POST("api/CompanyMainProduct/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyMainProductUpdateModel
    ): Result<Unit>

    @POST("api/CompanyMainProduct/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyMainProductId")
        companyMainProductId: Int
    ): Result<Unit>
}
