package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductLowPriceReportDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductLowPriceReportInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductLowPriceReportUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductLowPriceReportRepository {

    @GET("api/ProductLowPriceReport/GetProductLowPriceReportListAsync")
    suspend fun GetProductLowPriceReportListAsync():
            Result<List<ProductLowPriceReportDTO>>

    @GET("api/ProductLowPriceReport/GetProductLowPriceReportByIdAsync")
    suspend fun GetProductLowPriceReportByIdAsync(
        @Query("productLowPriceReportId")
        productLowPriceReportId: Int
    ): Result<ProductLowPriceReportUpdateModel?>

    @GET("api/ProductLowPriceReport/GetProductLowPriceReportByIdExtendedAsync")
    suspend fun GetProductLowPriceReportByIdExtendedAsync(
        @Query("productLowPriceReportId")
        productLowPriceReportId: Int
    ): Result<ProductLowPriceReportDTO?>

    @POST("api/ProductLowPriceReport/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductLowPriceReportInsertModel
    ): Result<Unit>

    @POST("api/ProductLowPriceReport/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductLowPriceReportUpdateModel
    ): Result<Unit>

    @POST("api/ProductLowPriceReport/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productLowPriceReportId")
        productLowPriceReportId: Int
    ): Result<Unit>
}
