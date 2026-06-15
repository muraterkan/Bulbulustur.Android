package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductIncorrectReportDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductIncorrectReportInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductIncorrectReportUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductIncorrectReportRepository {

    @GET("api/ProductIncorrectReport/GetProductIncorrectReportListAsync")
    suspend fun GetProductIncorrectReportListAsync():
            Result<List<ProductIncorrectReportDTO>>

    @GET("api/ProductIncorrectReport/GetProductIncorrectReportByIdAsync")
    suspend fun GetProductIncorrectReportByIdAsync(
        @Query("productIncorrectReportId")
        productIncorrectReportId: Int
    ): Result<ProductIncorrectReportUpdateModel?>

    @GET("api/ProductIncorrectReport/GetProductIncorrectReportByIdExtendedAsync")
    suspend fun GetProductIncorrectReportByIdExtendedAsync(
        @Query("productIncorrectReportId")
        productIncorrectReportId: Int
    ): Result<ProductIncorrectReportDTO?>

    @POST("api/ProductIncorrectReport/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductIncorrectReportInsertModel
    ): Result<Unit>

    @POST("api/ProductIncorrectReport/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductIncorrectReportUpdateModel
    ): Result<Unit>

    @POST("api/ProductIncorrectReport/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productIncorrectReportId")
        productIncorrectReportId: Int
    ): Result<Unit>
}
