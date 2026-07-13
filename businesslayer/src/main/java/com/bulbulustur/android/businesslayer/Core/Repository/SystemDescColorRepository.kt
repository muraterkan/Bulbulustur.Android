package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescColorDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescColorRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescColorInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescColorUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescColorRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescColorRepository {

    override suspend fun GetSystemDescColorsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescColorDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "MasterData/GetSystemDescColorsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescColorByIdAsync(
        systemDescColorId: Int
    ): Result<SystemDescColorUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescColorByIdAsync",
            query = "systemDescColorId=$systemDescColorId"
        )
    }

    override suspend fun GetSystemDescColorByIdExtendedAsync(
        systemDescColorId: Int
    ): Result<SystemDescColorDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescColorByIdExtendedAsync",
            query = "systemDescColorId=$systemDescColorId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescColorInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescColorUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescColorId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescColorId=$systemDescColorId"
        )
    }
}