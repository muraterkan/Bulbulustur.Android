package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMaritalStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescMaritalStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescMaritalStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMaritalStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescMaritalStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescMaritalStatusRepository {

    override suspend fun GetSystemDescMaritalStatusListAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescMaritalStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescMaritalStatussAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescMaritalStatusByIdAsync(
        systemDescMaritalStatusId: Int
    ): Result<SystemDescMaritalStatusUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescMaritalStatusByIdAsync",
            query = "systemDescMaritalStatusId=$systemDescMaritalStatusId"
        )
    }

    override suspend fun GetSystemDescMaritalStatusByIdExtendedAsync(
        systemDescMaritalStatusId: Int
    ): Result<SystemDescMaritalStatusDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescMaritalStatusByIdExtendedAsync",
            query = "systemDescMaritalStatusId=$systemDescMaritalStatusId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescMaritalStatusInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescMaritalStatusUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescMaritalStatusId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescMaritalStatusId=$systemDescMaritalStatusId"
        )
    }
}