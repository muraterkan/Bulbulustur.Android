package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyExportMarketDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyExportMarketInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyExportMarketUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyExportMarketRepository {

    @GET("api/CompanyExportMarket/GetCompanyExportMarketListAsync")
    suspend fun GetCompanyExportMarketListAsync():
            Result<List<CompanyExportMarketDTO>>

    @GET("api/CompanyExportMarket/GetCompanyExportMarketByIdAsync")
    suspend fun GetCompanyExportMarketByIdAsync(
        @Query("companyExportMarketId")
        companyExportMarketId: Int
    ): Result<CompanyExportMarketUpdateModel?>

    @GET("api/CompanyExportMarket/GetCompanyExportMarketByIdExtendedAsync")
    suspend fun GetCompanyExportMarketByIdExtendedAsync(
        @Query("companyExportMarketId")
        companyExportMarketId: Int
    ): Result<CompanyExportMarketDTO?>

    @POST("api/CompanyExportMarket/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyExportMarketInsertModel
    ): Result<Unit>

    @POST("api/CompanyExportMarket/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyExportMarketUpdateModel
    ): Result<Unit>

    @POST("api/CompanyExportMarket/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyExportMarketId")
        companyExportMarketId: Int
    ): Result<Unit>
}
