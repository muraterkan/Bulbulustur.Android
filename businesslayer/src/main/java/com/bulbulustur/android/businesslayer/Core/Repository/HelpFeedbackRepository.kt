package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.HelpFeedbackDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpFeedbackRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpFeedbackInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpFeedbackUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class HelpFeedbackRepository(
    private val apiClient: ApiClient = ApiClient
) : IHelpFeedbackRepository {

    override suspend fun GetHelpFeedbackListAsync(): Result<List<HelpFeedbackDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpFeedbackListAsync"
        )
    }

    override suspend fun GetHelpFeedbackByIdAsync(
        helpFeedbackId: Int
    ): Result<HelpFeedbackUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpFeedbackByIdAsync",
            query = "helpFeedbackId=$helpFeedbackId"
        )
    }

    override suspend fun GetHelpFeedbackByIdExtendedAsync(
        helpFeedbackId: Int
    ): Result<HelpFeedbackDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpFeedbackByIdExtendedAsync",
            query = "helpFeedbackId=$helpFeedbackId"
        )
    }

    override suspend fun InsertAsync(
        model: HelpFeedbackInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: HelpFeedbackUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        helpFeedbackId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "helpFeedbackId=$helpFeedbackId"
        )
    }
}