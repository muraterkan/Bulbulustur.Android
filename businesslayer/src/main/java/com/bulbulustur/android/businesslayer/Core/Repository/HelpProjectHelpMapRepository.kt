package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.HelpProjectHelpMapDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpProjectHelpMapRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpProjectHelpMapInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpProjectHelpMapUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class HelpProjectHelpMapRepository(
    private val apiClient: ApiClient = ApiClient
) : IHelpProjectHelpMapRepository {

    override suspend fun GetHelpProjectHelpMapListAsync(): Result<List<HelpProjectHelpMapDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpProjectHelpMapListAsync"
        )
    }

    override suspend fun GetHelpProjectHelpMapByIdAsync(
        helpProjectHelpMapId: Int
    ): Result<HelpProjectHelpMapUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpProjectHelpMapByIdAsync",
            query = "helpProjectHelpMapId=$helpProjectHelpMapId"
        )
    }

    override suspend fun GetHelpProjectHelpMapByIdExtendedAsync(
        helpProjectHelpMapId: Int
    ): Result<HelpProjectHelpMapDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpProjectHelpMapByIdExtendedAsync",
            query = "helpProjectHelpMapId=$helpProjectHelpMapId"
        )
    }

    override suspend fun InsertAsync(
        model: HelpProjectHelpMapInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: HelpProjectHelpMapUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        helpProjectHelpMapId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "helpProjectHelpMapId=$helpProjectHelpMapId"
        )
    }
}