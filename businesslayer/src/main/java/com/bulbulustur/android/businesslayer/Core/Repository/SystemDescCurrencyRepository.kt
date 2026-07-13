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

    override suspend fun GetSystemDescCurrenciesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescCurrencyDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "MasterData/GetSystemDescCurrenciesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescCurrencyByIdAsync(
        systemDescCurrencyId: Int
    ): Result<SystemDescCurrencyUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "SystemDescCurrency/GetSystemDescCurrencyByIdAsync",
            query = "systemDescCurrencyId=$systemDescCurrencyId"
        )
    }

    override suspend fun GetSystemDescCurrencyByIdExtendedAsync(
        languageId: Int,
        systemDescCurrencyId: Int
    ): Result<SystemDescCurrencyDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "SystemDescCurrency/GetSystemDescCurrencyByIdExtendedAsync",
            query = "languageId=$languageId&systemDescCurrencyId=$systemDescCurrencyId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescCurrencyInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "SystemDescCurrency/SystemDescCurrencyInsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescCurrencyUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "SystemDescCurrency/SystemDescCurrencyUpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescCurrencyId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "SystemDescCurrency/SystemDescCurrencyDelete",
            query = "systemDescCurrencyId=$systemDescCurrencyId"
        )
    }
}