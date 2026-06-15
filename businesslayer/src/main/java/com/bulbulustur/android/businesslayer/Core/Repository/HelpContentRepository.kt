package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.HelpContentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpContentRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpContentInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpContentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class HelpContentRepository(
    private val apiClient: ApiClient = ApiClient
) : IHelpContentRepository {

    override suspend fun GetHelpContentListAsync(): Result<List<HelpContentDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpContentListAsync"
        )
    }

    override suspend fun GetHelpContentByIdAsync(
        helpContentId: Int
    ): Result<HelpContentUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpContentByIdAsync",
            query = "helpContentId=$helpContentId"
        )
    }

    override suspend fun GetHelpContentByIdExtendedAsync(
        helpContentId: Int
    ): Result<HelpContentDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpContentByIdExtendedAsync",
            query = "helpContentId=$helpContentId"
        )
    }

    override suspend fun InsertAsync(
        model: HelpContentInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: HelpContentUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        helpContentId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "helpContentId=$helpContentId"
        )
    }
}