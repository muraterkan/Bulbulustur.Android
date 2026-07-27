package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescRuleSeverityDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescRuleSeverityRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescRuleSeverityInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescRuleSeverityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRuleSeverityRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRuleSeverityRepository {

    override suspend fun GetSystemDescRuleSeveritiesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescRuleSeverityDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescRuleSeveritiesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescRuleSeverityByIdAsync(
        systemDescRuleSeverityId: Int
    ): Result<SystemDescRuleSeverityUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescRuleSeverityByIdAsync",
            query = "systemDescRuleSeverityId=$systemDescRuleSeverityId"
        )
    }

    override suspend fun GetSystemDescRuleSeverityByIdExtendedAsync(
        languageId: Int,
        systemDescRuleSeverityId: Int
    ): Result<SystemDescRuleSeverityDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescRuleSeverityByIdExtendedAsync",
            query = "languageId=$languageId&systemDescRuleSeverityId=$systemDescRuleSeverityId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescRuleSeverityInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescRuleSeverityAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescRuleSeverityUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescRuleSeverityAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescRuleSeverityId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescRuleSeverityAsync",
            query = "systemDescRuleSeverityId=$systemDescRuleSeverityId"
        )
    }
}