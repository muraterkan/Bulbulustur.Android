package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.HelpPopularTopicDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpPopularTopicRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpPopularTopicInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpPopularTopicUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class HelpPopularTopicRepository(
    private val apiClient: ApiClient = ApiClient
) : IHelpPopularTopicRepository {

    override suspend fun GetHelpPopularTopicListAsync(): Result<List<HelpPopularTopicDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpPopularTopicListAsync"
        )
    }

    override suspend fun GetHelpPopularTopicByIdAsync(
        helpPopularTopicId: Int
    ): Result<HelpPopularTopicUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpPopularTopicByIdAsync",
            query = "helpPopularTopicId=$helpPopularTopicId"
        )
    }

    override suspend fun GetHelpPopularTopicByIdExtendedAsync(
        helpPopularTopicId: Int
    ): Result<HelpPopularTopicDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpPopularTopicByIdExtendedAsync",
            query = "helpPopularTopicId=$helpPopularTopicId"
        )
    }

    override suspend fun InsertAsync(
        model: HelpPopularTopicInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: HelpPopularTopicUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        helpPopularTopicId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "helpPopularTopicId=$helpPopularTopicId"
        )
    }
}