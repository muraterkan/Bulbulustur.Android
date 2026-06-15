package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.HelpCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IHelpCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpCategoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class HelpCategoryRepository(
    private val apiClient: ApiClient = ApiClient
) : IHelpCategoryRepository {

    override suspend fun GetHelpCategoryListAsync(): Result<List<HelpCategoryDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpCategoryListAsync"
        )
    }

    override suspend fun GetHelpCategoryByIdAsync(
        helpCategoryId: Int
    ): Result<HelpCategoryUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpCategoryByIdAsync",
            query = "helpCategoryId=$helpCategoryId"
        )
    }

    override suspend fun GetHelpCategoryByIdExtendedAsync(
        helpCategoryId: Int
    ): Result<HelpCategoryDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetHelpCategoryByIdExtendedAsync",
            query = "helpCategoryId=$helpCategoryId"
        )
    }

    override suspend fun InsertAsync(
        model: HelpCategoryInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: HelpCategoryUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        helpCategoryId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "helpCategoryId=$helpCategoryId"
        )
    }
}