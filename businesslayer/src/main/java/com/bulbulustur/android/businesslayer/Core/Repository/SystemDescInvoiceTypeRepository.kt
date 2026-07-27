package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescInvoiceTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescInvoiceTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescInvoiceTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescInvoiceTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescInvoiceTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescInvoiceTypeRepository {

    override suspend fun GetSystemDescInvoiceTypesAsync(
        count: Int
    ): Result<List<SystemDescInvoiceTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescInvoiceTypesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetSystemDescInvoiceTypeByIdAsync(
        systemDescInvoiceTypeId: Int
    ): Result<SystemDescInvoiceTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescInvoiceTypeByIdAsync",
            query = "systemDescInvoiceTypeId=$systemDescInvoiceTypeId"
        )
    }

    override suspend fun GetSystemDescInvoiceTypeByIdExtendedAsync(
        systemDescInvoiceTypeId: Int
    ): Result<SystemDescInvoiceTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescInvoiceTypeByIdExtendedAsync",
            query = "systemDescInvoiceTypeId=$systemDescInvoiceTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescInvoiceTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "InsertSystemDescInvoiceTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescInvoiceTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "UpdateSystemDescInvoiceTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescInvoiceTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "DeleteSystemDescInvoiceTypeAsync",
            query = "systemDescInvoiceTypeId=$systemDescInvoiceTypeId"
        )
    }
}