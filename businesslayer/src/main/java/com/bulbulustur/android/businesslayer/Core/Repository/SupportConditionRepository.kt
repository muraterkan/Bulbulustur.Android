package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SupportConditionDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISupportConditionRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SupportConditionInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SupportConditionUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SupportConditionRepository(
    private val apiClient: ApiClient = ApiClient
) : ISupportConditionRepository {

    override suspend fun GetSupportConditionListAsync(): Result<List<SupportConditionDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSupportConditionListAsync"
        )
    }

    override suspend fun GetSupportConditionByIdAsync(
        supportConditionId: Int
    ): Result<SupportConditionUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSupportConditionByIdAsync",
            query = "supportConditionId=$supportConditionId"
        )
    }

    override suspend fun GetSupportConditionByIdExtendedAsync(supportConditionId: Int): Result<SupportConditionDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSupportConditionByIdExtendedAsync",
            query = "supportConditionId=$supportConditionId"
        )
    }

    override suspend fun InsertAsync(
        model: SupportConditionInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SupportConditionUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        supportConditionId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "supportConditionId=$supportConditionId"
        )
    }
}