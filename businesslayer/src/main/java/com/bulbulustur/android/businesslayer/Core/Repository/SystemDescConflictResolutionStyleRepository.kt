package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescConflictResolutionStyleDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescConflictResolutionStyleRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescConflictResolutionStyleInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescConflictResolutionStyleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescConflictResolutionStyleRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescConflictResolutionStyleRepository {

    override suspend fun GetSystemDescConflictResolutionStylesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescConflictResolutionStyleDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescConflictResolutionStylesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescConflictResolutionStyleByIdAsync(
        systemDescConflictResolutionStyleId: Int
    ): Result<SystemDescConflictResolutionStyleUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescConflictResolutionStyleByIdAsync",
            query = "systemDescConflictResolutionStyleId=$systemDescConflictResolutionStyleId"
        )
    }

    override suspend fun GetSystemDescConflictResolutionStyleByIdExtendedAsync(
        languageId: Int,
        systemDescConflictResolutionStyleId: Int
    ): Result<SystemDescConflictResolutionStyleDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescConflictResolutionStyleByIdExtendedAsync",
            query = "languageId=$languageId&systemDescConflictResolutionStyleId=$systemDescConflictResolutionStyleId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescConflictResolutionStyleInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescConflictResolutionStyleAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescConflictResolutionStyleUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescConflictResolutionStyleAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescConflictResolutionStyleId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescConflictResolutionStyleAsync",
            query = "systemDescConflictResolutionStyleId=$systemDescConflictResolutionStyleId"
        )
    }
}