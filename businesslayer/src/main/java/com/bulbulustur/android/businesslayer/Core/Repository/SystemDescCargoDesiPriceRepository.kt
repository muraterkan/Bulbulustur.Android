package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoDesiPriceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCargoDesiPriceRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCargoDesiPriceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoDesiPriceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescCargoDesiPriceRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescCargoDesiPriceRepository {

    override suspend fun GetSystemDescCargoDesiPricesAsync(
        count: Int
    ): Result<List<SystemDescCargoDesiPriceDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescCargoDesiPricesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetSystemDescCargoDesiPriceByIdAsync(
        systemDescCargoDesiPriceId: Int
    ): Result<SystemDescCargoDesiPriceUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescCargoDesiPriceByIdAsync",
            query = "systemDescCargoDesiPriceId=$systemDescCargoDesiPriceId"
        )
    }

    override suspend fun GetSystemDescCargoDesiPriceByIdExtendedAsync(
        systemDescCargoDesiPriceId: Int
    ): Result<SystemDescCargoDesiPriceDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescCargoDesiPriceByIdExtendedAsync",
            query = "systemDescCargoDesiPriceId=$systemDescCargoDesiPriceId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescCargoDesiPriceInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "InsertSystemDescCargoDesiPriceAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescCargoDesiPriceUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "UpdateSystemDescCargoDesiPriceAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescCargoDesiPriceId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "DeleteSystemDescCargoDesiPriceAsync",
            query = "systemDescCargoDesiPriceId=$systemDescCargoDesiPriceId"
        )
    }
}