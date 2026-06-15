package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.HelpDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class HelpRepository(
    private val apiClient: ApiClient = ApiClient
) : IHelpRepository {

    override suspend fun GetHelpListAsync(): Result<List<HelpDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpListAsync"
        )
    }

    override suspend fun GetHelpByIdAsync(
        helpId: Int
    ): Result<HelpUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpByIdAsync",
            query = "helpId=$helpId"
        )
    }

    override suspend fun GetHelpByIdExtendedAsync(
        helpId: Int
    ): Result<HelpDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpByIdExtendedAsync",
            query = "helpId=$helpId"
        )
    }

    override suspend fun InsertAsync(
        model: HelpInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: HelpUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        helpId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "helpId=$helpId"
        )
    }
}