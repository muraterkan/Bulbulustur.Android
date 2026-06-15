package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescEducationLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescEducationLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescEducationLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescEducationLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescEducationLanguageRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescEducationLanguageRepository {

    override suspend fun GetSystemDescEducationLanguageListAsync(): Result<List<SystemDescEducationLanguageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescEducationLanguageListAsync"
        )
    }

    override suspend fun GetSystemDescEducationLanguageByIdAsync(
        systemDescEducationLanguageId: Int
    ): Result<SystemDescEducationLanguageUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescEducationLanguageByIdAsync",
            query = "systemDescEducationLanguageId=$systemDescEducationLanguageId"
        )
    }

    override suspend fun GetSystemDescEducationLanguageByIdExtendedAsync(
        systemDescEducationLanguageId: Int
    ): Result<SystemDescEducationLanguageDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescEducationLanguageByIdExtendedAsync",
            query = "systemDescEducationLanguageId=$systemDescEducationLanguageId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescEducationLanguageInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescEducationLanguageUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescEducationLanguageId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescEducationLanguageId=$systemDescEducationLanguageId"
        )
    }
}