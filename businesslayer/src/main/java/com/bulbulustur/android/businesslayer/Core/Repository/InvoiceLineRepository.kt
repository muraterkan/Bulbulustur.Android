package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.InvoiceLineDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IInvoiceLineRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.InvoiceLineInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.InvoiceLineUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class InvoiceLineRepository(
    private val apiClient: ApiClient = ApiClient
) : IInvoiceLineRepository {

    override suspend fun GetInvoiceLineListAsync(): Result<List<InvoiceLineDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetInvoiceLineListAsync"
        )
    }

    override suspend fun GetInvoiceLineByIdAsync(
        invoiceLineId: Int
    ): Result<InvoiceLineUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetInvoiceLineByIdAsync",
            query = "invoiceLineId=$invoiceLineId"
        )
    }

    override suspend fun GetInvoiceLineByIdExtendedAsync(
        invoiceLineId: Int
    ): Result<InvoiceLineDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetInvoiceLineByIdExtendedAsync",
            query = "invoiceLineId=$invoiceLineId"
        )
    }

    override suspend fun InsertAsync(
        model: InvoiceLineInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: InvoiceLineUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        invoiceLineId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "invoiceLineId=$invoiceLineId"
        )
    }
}