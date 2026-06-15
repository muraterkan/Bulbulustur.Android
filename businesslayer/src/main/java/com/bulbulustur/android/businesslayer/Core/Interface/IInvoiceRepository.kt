package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.InvoiceDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.InvoiceUpdateModel

interface IInvoiceRepository {

    suspend fun GetInvoiceListAsync(): Result<List<InvoiceDTO>>

    suspend fun GetInvoiceByIdAsync(
        invoiceId: Int
    ): Result<InvoiceUpdateModel?>

    suspend fun GetInvoiceByIdExtendedAsync(
        invoiceId: Int
    ): Result<InvoiceDTO?>
}
