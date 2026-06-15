package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.InvoiceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IInvoiceRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.InvoiceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.InvoiceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class InvoiceRepository(
    private val apiClient: ApiClient = ApiClient
) : IInvoiceRepository {

    override suspend fun GetInvoiceListAsync(): Result<List<InvoiceDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetInvoiceListAsync"
        )
    }

    override suspend fun GetInvoiceByIdAsync(
        invoiceId: Int
    ): Result<InvoiceUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetInvoiceByIdAsync",
            query = "invoiceId=$invoiceId"
        )
    }

    override suspend fun GetInvoiceByIdExtendedAsync(
        invoiceId: Int
    ): Result<InvoiceDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetInvoiceByIdExtendedAsync",
            query = "invoiceId=$invoiceId"
        )
    }

    override suspend fun InsertAsync(
        model: InvoiceInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: InvoiceUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        invoiceId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "invoiceId=$invoiceId"
        )
    }
}