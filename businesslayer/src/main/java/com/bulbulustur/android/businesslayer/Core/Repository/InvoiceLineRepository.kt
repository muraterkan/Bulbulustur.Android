package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.InvoiceLineDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IInvoiceLineRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.InvoiceLineUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class InvoiceLineRepository(
    private val apiClient: ApiClient
) : IInvoiceLineRepository {

    override suspend fun GetInvoiceLineListAsync(): Result<List<InvoiceLineDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetInvoiceLineByIdAsync(
        invoiceLineId: Int
    ): Result<InvoiceLineUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetInvoiceLineByIdExtendedAsync(
        invoiceLineId: Int
    ): Result<InvoiceLineDTO?> {
        TODO("Not implemented yet")
    }
}
