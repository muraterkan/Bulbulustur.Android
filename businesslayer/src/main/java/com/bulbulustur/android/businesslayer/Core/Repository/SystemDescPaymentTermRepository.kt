package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPaymentTermDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescPaymentTermRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPaymentTermInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPaymentTermUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescPaymentTermRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescPaymentTermRepository {

    override suspend fun GetSystemDescPaymentTermsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescPaymentTermDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "MasterData/GetSystemDescPaymentTermsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescPaymentTermByIdAsync(
        systemDescPaymentTermId: Int
    ): Result<SystemDescPaymentTermUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPaymentTermByIdAsync",
            query = "systemDescPaymentTermId=$systemDescPaymentTermId"
        )
    }

    override suspend fun GetSystemDescPaymentTermByIdExtendedAsync(
        systemDescPaymentTermId: Int
    ): Result<SystemDescPaymentTermDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPaymentTermByIdExtendedAsync",
            query = "systemDescPaymentTermId=$systemDescPaymentTermId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescPaymentTermInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescPaymentTermUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescPaymentTermId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescPaymentTermId=$systemDescPaymentTermId"
        )
    }
}