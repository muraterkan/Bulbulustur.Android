package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescVatRateDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescVatRateRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescVatRateInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescVatRateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescVatRateRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescVatRateRepository {

    override suspend fun GetSystemDescVatRateListAsync(): Result<List<SystemDescVatRateDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescVatRateListAsync"
        )
    }

    override suspend fun GetSystemDescVatRateByIdAsync(
        systemDescVatRateId: Int
    ): Result<SystemDescVatRateUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescVatRateByIdAsync",
            query = "systemDescVatRateId=$systemDescVatRateId"
        )
    }

    override suspend fun GetSystemDescVatRateByIdExtendedAsync(
        systemDescVatRateId: Int
    ): Result<SystemDescVatRateDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescVatRateByIdExtendedAsync",
            query = "systemDescVatRateId=$systemDescVatRateId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescVatRateInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescVatRateUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescVatRateId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescVatRateId=$systemDescVatRateId"
        )
    }
}