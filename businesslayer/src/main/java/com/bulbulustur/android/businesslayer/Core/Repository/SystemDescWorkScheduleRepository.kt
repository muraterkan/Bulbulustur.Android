package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescWorkScheduleDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescWorkScheduleRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescWorkScheduleInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescWorkScheduleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescWorkScheduleRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescWorkScheduleRepository {

    override suspend fun GetSystemDescWorkSchedulesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescWorkScheduleDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescWorkSchedulesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescWorkScheduleByIdAsync(
        systemDescWorkScheduleId: Int
    ): Result<SystemDescWorkScheduleUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescWorkScheduleByIdAsync",
            query = "systemDescWorkScheduleId=$systemDescWorkScheduleId"
        )
    }

    override suspend fun GetSystemDescWorkScheduleByIdExtendedAsync(
        languageId: Int,
        systemDescWorkScheduleId: Int
    ): Result<SystemDescWorkScheduleDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescWorkScheduleByIdExtendedAsync",
            query = "languageId=$languageId&systemDescWorkScheduleId=$systemDescWorkScheduleId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescWorkScheduleInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescWorkScheduleAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescWorkScheduleUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescWorkScheduleAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescWorkScheduleId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescWorkScheduleAsync",
            query = "systemDescWorkScheduleId=$systemDescWorkScheduleId"
        )
    }
}