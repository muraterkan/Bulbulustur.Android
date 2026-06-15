package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.HelpProjectDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpProjectRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpProjectInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpProjectUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class HelpProjectRepository(
    private val apiClient: ApiClient = ApiClient
) : IHelpProjectRepository {

    override suspend fun GetHelpProjectListAsync(): Result<List<HelpProjectDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpProjectListAsync"
        )
    }

    override suspend fun GetHelpProjectByIdAsync(
        helpProjectId: Int
    ): Result<HelpProjectUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpProjectByIdAsync",
            query = "helpProjectId=$helpProjectId"
        )
    }

    override suspend fun GetHelpProjectByIdExtendedAsync(
        helpProjectId: Int
    ): Result<HelpProjectDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpProjectByIdExtendedAsync",
            query = "helpProjectId=$helpProjectId"
        )
    }

    override suspend fun InsertAsync(
        model: HelpProjectInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: HelpProjectUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        helpProjectId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "helpProjectId=$helpProjectId"
        )
    }
}