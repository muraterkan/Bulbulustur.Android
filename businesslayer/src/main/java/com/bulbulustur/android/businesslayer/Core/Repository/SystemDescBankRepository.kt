package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBankDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescBankRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescBankInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescBankUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescBankRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescBankRepository {

    override suspend fun GetSystemDescBankListAsync(): Result<List<SystemDescBankDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescBankListAsync"
        )
    }

    override suspend fun GetSystemDescBankByIdAsync(
        systemDescBankId: Int
    ): Result<SystemDescBankUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescBankByIdAsync",
            query = "systemDescBankId=$systemDescBankId"
        )
    }

    override suspend fun GetSystemDescBankByIdExtendedAsync(
        systemDescBankId: Int
    ): Result<SystemDescBankDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescBankByIdExtendedAsync",
            query = "systemDescBankId=$systemDescBankId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescBankInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescBankUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescBankId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescBankId=$systemDescBankId"
        )
    }
}