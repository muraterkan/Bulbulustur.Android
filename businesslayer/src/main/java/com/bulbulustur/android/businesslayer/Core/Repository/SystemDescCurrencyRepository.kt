package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCurrencyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCurrencyRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCurrencyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCurrencyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescCurrencyRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescCurrencyRepository {

    override suspend fun GetSystemDescCurrencyListAsync(): Result<List<SystemDescCurrencyDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCurrencyListAsync"
        )
    }

    override suspend fun GetSystemDescCurrencyByIdAsync(
        systemDescCurrencyId: Int
    ): Result<SystemDescCurrencyUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCurrencyByIdAsync",
            query = "systemDescCurrencyId=$systemDescCurrencyId"
        )
    }

    override suspend fun GetSystemDescCurrencyByIdExtendedAsync(
        systemDescCurrencyId: Int
    ): Result<SystemDescCurrencyDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCurrencyByIdExtendedAsync",
            query = "systemDescCurrencyId=$systemDescCurrencyId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescCurrencyInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescCurrencyUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescCurrencyId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescCurrencyId=$systemDescCurrencyId"
        )
    }
}