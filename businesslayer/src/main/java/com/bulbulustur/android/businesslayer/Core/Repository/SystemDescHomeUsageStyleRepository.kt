package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescHomeUsageStyleDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescHomeUsageStyleRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescHomeUsageStyleInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescHomeUsageStyleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescHomeUsageStyleRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescHomeUsageStyleRepository {

    override suspend fun GetSystemDescHomeUsageStylesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescHomeUsageStyleDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescHomeUsageStylesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescHomeUsageStyleByIdAsync(
        systemDescHomeUsageStyleId: Int
    ): Result<SystemDescHomeUsageStyleUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescHomeUsageStyleByIdAsync",
            query = "systemDescHomeUsageStyleId=$systemDescHomeUsageStyleId"
        )
    }

    override suspend fun GetSystemDescHomeUsageStyleByIdExtendedAsync(
        languageId: Int,
        systemDescHomeUsageStyleId: Int
    ): Result<SystemDescHomeUsageStyleDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescHomeUsageStyleByIdExtendedAsync",
            query = "languageId=$languageId&systemDescHomeUsageStyleId=$systemDescHomeUsageStyleId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescHomeUsageStyleInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescHomeUsageStyleAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescHomeUsageStyleUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescHomeUsageStyleAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescHomeUsageStyleId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescHomeUsageStyleAsync",
            query = "systemDescHomeUsageStyleId=$systemDescHomeUsageStyleId"
        )
    }
}