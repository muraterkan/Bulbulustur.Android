package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TutorialDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITutorialRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TutorialInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TutorialUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TutorialRepository(
    private val apiClient: ApiClient = ApiClient
) : ITutorialRepository {

    override suspend fun GetTutorialListAsync(): Result<List<TutorialDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetTutorialListAsync"
        )
    }

    override suspend fun GetTutorialByIdAsync(
        tutorialId: Int
    ): Result<TutorialUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetTutorialByIdAsync",
            query = "tutorialId=$tutorialId"
        )
    }

    override suspend fun GetTutorialByIdExtendedAsync(
        tutorialId: Int
    ): Result<TutorialDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetTutorialByIdExtendedAsync",
            query = "tutorialId=$tutorialId"
        )
    }

    override suspend fun InsertAsync(
        model: TutorialInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: TutorialUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        tutorialId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "tutorialId=$tutorialId"
        )
    }
}