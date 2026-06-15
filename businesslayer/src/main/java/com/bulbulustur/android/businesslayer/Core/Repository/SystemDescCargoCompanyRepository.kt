package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoCompanyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCargoCompanyRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCargoCompanyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoCompanyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescCargoCompanyRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescCargoCompanyRepository {

    override suspend fun GetSystemDescCargoCompanyListAsync(): Result<List<SystemDescCargoCompanyDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCargoCompanyListAsync"
        )
    }

    override suspend fun GetSystemDescCargoCompanyByIdAsync(
        systemDescCargoCompanyId: Int
    ): Result<SystemDescCargoCompanyUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCargoCompanyByIdAsync",
            query = "systemDescCargoCompanyId=$systemDescCargoCompanyId"
        )
    }

    override suspend fun GetSystemDescCargoCompanyByIdExtendedAsync(
        systemDescCargoCompanyId: Int
    ): Result<SystemDescCargoCompanyDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCargoCompanyByIdExtendedAsync",
            query = "systemDescCargoCompanyId=$systemDescCargoCompanyId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescCargoCompanyInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescCargoCompanyUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescCargoCompanyId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescCargoCompanyId=$systemDescCargoCompanyId"
        )
    }
}