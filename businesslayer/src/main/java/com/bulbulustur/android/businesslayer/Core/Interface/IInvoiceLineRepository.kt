package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.InvoiceLineDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.InvoiceLineUpdateModel

interface IInvoiceLineRepository {

    suspend fun GetInvoiceLineListAsync(): Result<List<InvoiceLineDTO>>

    suspend fun GetInvoiceLineByIdAsync(
        invoiceLineId: Int
    ): Result<InvoiceLineUpdateModel?>

    suspend fun GetInvoiceLineByIdExtendedAsync(
        invoiceLineId: Int
    ): Result<InvoiceLineDTO?>
}
