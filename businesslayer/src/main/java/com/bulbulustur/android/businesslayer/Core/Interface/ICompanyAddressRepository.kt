package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyAddressDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyAddressInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyAddressRepository {

    @GET("api/CompanyAddress/GetCompanyAddressListAsync")
    suspend fun GetCompanyAddressListAsync():
            Result<List<CompanyAddressDTO>>

    @GET("api/CompanyAddress/GetCompanyAddressByIdAsync")
    suspend fun GetCompanyAddressByIdAsync(
        @Query("companyAddressId")
        companyAddressId: Int
    ): Result<CompanyAddressUpdateModel?>

    @GET("api/CompanyAddress/GetCompanyAddressByIdExtendedAsync")
    suspend fun GetCompanyAddressByIdExtendedAsync(
        @Query("companyAddressId")
        companyAddressId: Int
    ): Result<CompanyAddressDTO?>

    @POST("api/CompanyAddress/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyAddressInsertModel
    ): Result<Unit>

    @POST("api/CompanyAddress/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyAddressUpdateModel
    ): Result<Unit>

    @POST("api/CompanyAddress/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyAddressId")
        companyAddressId: Int
    ): Result<Unit>
}
