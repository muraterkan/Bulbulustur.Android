package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.InvoiceLineDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.InvoiceLineInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.InvoiceLineUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IInvoiceLineRepository {

    @GET("api/InvoiceLine/GetInvoiceLineListAsync")
    suspend fun GetInvoiceLineListAsync():
            Result<List<InvoiceLineDTO>>

    @GET("api/InvoiceLine/GetInvoiceLineByIdAsync")
    suspend fun GetInvoiceLineByIdAsync(
        @Query("invoiceLineId")
        invoiceLineId: Int
    ): Result<InvoiceLineUpdateModel?>

    @GET("api/InvoiceLine/GetInvoiceLineByIdExtendedAsync")
    suspend fun GetInvoiceLineByIdExtendedAsync(
        @Query("invoiceLineId")
        invoiceLineId: Int
    ): Result<InvoiceLineDTO?>

    @POST("api/InvoiceLine/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: InvoiceLineInsertModel
    ): Result<Unit>

    @POST("api/InvoiceLine/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: InvoiceLineUpdateModel
    ): Result<Unit>

    @POST("api/InvoiceLine/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("invoiceLineId")
        invoiceLineId: Int
    ): Result<Unit>
}
