package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescNoiseToleranceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescNoiseToleranceRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescNoiseToleranceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescNoiseToleranceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescNoiseToleranceRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescNoiseToleranceRepository {

    override suspend fun GetSystemDescNoiseTolerancesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescNoiseToleranceDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescNoiseTolerancesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescNoiseToleranceByIdAsync(
        systemDescNoiseToleranceId: Int
    ): Result<SystemDescNoiseToleranceUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescNoiseToleranceByIdAsync",
            query = "systemDescNoiseToleranceId=$systemDescNoiseToleranceId"
        )
    }

    override suspend fun GetSystemDescNoiseToleranceByIdExtendedAsync(
        languageId: Int,
        systemDescNoiseToleranceId: Int
    ): Result<SystemDescNoiseToleranceDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescNoiseToleranceByIdExtendedAsync",
            query = "languageId=$languageId&systemDescNoiseToleranceId=$systemDescNoiseToleranceId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescNoiseToleranceInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescNoiseToleranceAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescNoiseToleranceUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescNoiseToleranceAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescNoiseToleranceId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescNoiseToleranceAsync",
            query = "systemDescNoiseToleranceId=$systemDescNoiseToleranceId"
        )
    }
}