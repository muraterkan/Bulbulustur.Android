package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.InvoiceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IInvoiceRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.InvoiceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class InvoiceRepository(
    private val apiClient: ApiClient
) : IInvoiceRepository {

    override suspend fun GetInvoiceListAsync(): Result<List<InvoiceDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetInvoiceByIdAsync(
        invoiceId: Int
    ): Result<InvoiceUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetInvoiceByIdExtendedAsync(
        invoiceId: Int
    ): Result<InvoiceDTO?> {
        TODO("Not implemented yet")
    }
}
