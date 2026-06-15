package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCurrencyLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCurrencyLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCurrencyLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCurrencyLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescCurrencyLanguageRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescCurrencyLanguageRepository {

    override suspend fun GetSystemDescCurrencyLanguageListAsync(): Result<List<SystemDescCurrencyLanguageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCurrencyLanguageListAsync"
        )
    }

    override suspend fun GetSystemDescCurrencyLanguageByIdAsync(
        systemDescCurrencyLanguageId: Int
    ): Result<SystemDescCurrencyLanguageUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCurrencyLanguageByIdAsync",
            query = "systemDescCurrencyLanguageId=$systemDescCurrencyLanguageId"
        )
    }

    override suspend fun GetSystemDescCurrencyLanguageByIdExtendedAsync(
        systemDescCurrencyLanguageId: Int
    ): Result<SystemDescCurrencyLanguageDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCurrencyLanguageByIdExtendedAsync",
            query = "systemDescCurrencyLanguageId=$systemDescCurrencyLanguageId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescCurrencyLanguageInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescCurrencyLanguageUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescCurrencyLanguageId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescCurrencyLanguageId=$systemDescCurrencyLanguageId"
        )
    }
}