package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyBusinessTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyBusinessTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyBusinessTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyBusinessTypeRepository {

    @GET("api/CompanyBusinessType/GetCompanyBusinessTypeListAsync")
    suspend fun GetCompanyBusinessTypeListAsync():
            Result<List<CompanyBusinessTypeDTO>>

    @GET("api/CompanyBusinessType/GetCompanyBusinessTypeByIdAsync")
    suspend fun GetCompanyBusinessTypeByIdAsync(
        @Query("companyBusinessTypeId")
        companyBusinessTypeId: Int
    ): Result<CompanyBusinessTypeUpdateModel?>

    @GET("api/CompanyBusinessType/GetCompanyBusinessTypeByIdExtendedAsync")
    suspend fun GetCompanyBusinessTypeByIdExtendedAsync(
        @Query("companyBusinessTypeId")
        companyBusinessTypeId: Int
    ): Result<CompanyBusinessTypeDTO?>

    @POST("api/CompanyBusinessType/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyBusinessTypeInsertModel
    ): Result<Unit>

    @POST("api/CompanyBusinessType/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyBusinessTypeUpdateModel
    ): Result<Unit>

    @POST("api/CompanyBusinessType/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyBusinessTypeId")
        companyBusinessTypeId: Int
    ): Result<Unit>
}
