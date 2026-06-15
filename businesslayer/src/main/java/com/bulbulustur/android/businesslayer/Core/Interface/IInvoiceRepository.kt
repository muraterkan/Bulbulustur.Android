package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.InvoiceDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.InvoiceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.InvoiceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IInvoiceRepository {

    @GET("api/Invoice/GetInvoiceListAsync")
    suspend fun GetInvoiceListAsync():
            Result<List<InvoiceDTO>>

    @GET("api/Invoice/GetInvoiceByIdAsync")
    suspend fun GetInvoiceByIdAsync(
        @Query("invoiceId")
        invoiceId: Int
    ): Result<InvoiceUpdateModel?>

    @GET("api/Invoice/GetInvoiceByIdExtendedAsync")
    suspend fun GetInvoiceByIdExtendedAsync(
        @Query("invoiceId")
        invoiceId: Int
    ): Result<InvoiceDTO?>

    @POST("api/Invoice/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: InvoiceInsertModel
    ): Result<Unit>

    @POST("api/Invoice/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: InvoiceUpdateModel
    ): Result<Unit>

    @POST("api/Invoice/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("invoiceId")
        invoiceId: Int
    ): Result<Unit>
}
