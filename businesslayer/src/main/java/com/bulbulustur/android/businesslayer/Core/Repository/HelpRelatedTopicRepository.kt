package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.HelpRelatedTopicDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpRelatedTopicRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpRelatedTopicInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpRelatedTopicUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class HelpRelatedTopicRepository(
    private val apiClient: ApiClient = ApiClient
) : IHelpRelatedTopicRepository {

    override suspend fun GetHelpRelatedTopicListAsync(): Result<List<HelpRelatedTopicDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpRelatedTopicListAsync"
        )
    }

    override suspend fun GetHelpRelatedTopicByIdAsync(
        helpRelatedTopicId: Int
    ): Result<HelpRelatedTopicUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpRelatedTopicByIdAsync",
            query = "helpRelatedTopicId=$helpRelatedTopicId"
        )
    }

    override suspend fun GetHelpRelatedTopicByIdExtendedAsync(
        helpRelatedTopicId: Int
    ): Result<HelpRelatedTopicDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpRelatedTopicByIdExtendedAsync",
            query = "helpRelatedTopicId=$helpRelatedTopicId"
        )
    }

    override suspend fun InsertAsync(
        model: HelpRelatedTopicInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: HelpRelatedTopicUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        helpRelatedTopicId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "helpRelatedTopicId=$helpRelatedTopicId"
        )
    }
}