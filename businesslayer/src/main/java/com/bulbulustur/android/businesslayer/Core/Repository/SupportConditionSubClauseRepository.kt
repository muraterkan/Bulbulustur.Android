package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SupportConditionSubClauseDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISupportConditionSubClauseRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SupportConditionSubClauseInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SupportConditionSubClauseUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SupportConditionSubClauseRepository(
    private val apiClient: ApiClient = ApiClient
) : ISupportConditionSubClauseRepository {

    override suspend fun GetSupportConditionSubClauseListAsync(): Result<List<SupportConditionSubClauseDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSupportConditionSubClauseListAsync"
        )
    }

    override suspend fun GetSupportConditionSubClauseByIdAsync(
        supportConditionSubClauseId: Int
    ): Result<SupportConditionSubClauseUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSupportConditionSubClauseByIdAsync",
            query = "supportConditionSubClauseId=$supportConditionSubClauseId"
        )
    }

    override suspend fun GetSupportConditionSubClauseByIdExtendedAsync(
        supportConditionSubClauseId: Int
    ): Result<SupportConditionSubClauseDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSupportConditionSubClauseByIdExtendedAsync",
            query = "supportConditionSubClauseId=$supportConditionSubClauseId"
        )
    }

    override suspend fun InsertAsync(
        model: SupportConditionSubClauseInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SupportConditionSubClauseUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        supportConditionSubClauseId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "supportConditionSubClauseId=$supportConditionSubClauseId"
        )
    }
}