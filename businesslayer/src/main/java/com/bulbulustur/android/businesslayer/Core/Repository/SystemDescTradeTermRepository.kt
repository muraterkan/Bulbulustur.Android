package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescTradeTermDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescTradeTermRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescTradeTermInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescTradeTermUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescTradeTermRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescTradeTermRepository {

    override suspend fun GetSystemDescTradeTermListAsync(): Result<List<SystemDescTradeTermDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescTradeTermListAsync"
        )
    }

    override suspend fun GetSystemDescTradeTermByIdAsync(
        systemDescTradeTermId: Int
    ): Result<SystemDescTradeTermUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescTradeTermByIdAsync",
            query = "systemDescTradeTermId=$systemDescTradeTermId"
        )
    }

    override suspend fun GetSystemDescTradeTermByIdExtendedAsync(
        systemDescTradeTermId: Int
    ): Result<SystemDescTradeTermDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescTradeTermByIdExtendedAsync",
            query = "systemDescTradeTermId=$systemDescTradeTermId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescTradeTermInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescTradeTermUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescTradeTermId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescTradeTermId=$systemDescTradeTermId"
        )
    }
}