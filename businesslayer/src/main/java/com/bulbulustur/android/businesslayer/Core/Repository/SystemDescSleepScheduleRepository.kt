package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSleepScheduleDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescSleepScheduleRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescSleepScheduleInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescSleepScheduleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescSleepScheduleRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescSleepScheduleRepository {

    override suspend fun GetSystemDescSleepSchedulesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescSleepScheduleDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescSleepSchedulesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescSleepScheduleByIdAsync(
        systemDescSleepScheduleId: Int
    ): Result<SystemDescSleepScheduleUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescSleepScheduleByIdAsync",
            query = "systemDescSleepScheduleId=$systemDescSleepScheduleId"
        )
    }

    override suspend fun GetSystemDescSleepScheduleByIdExtendedAsync(
        languageId: Int,
        systemDescSleepScheduleId: Int
    ): Result<SystemDescSleepScheduleDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescSleepScheduleByIdExtendedAsync",
            query = "languageId=$languageId&systemDescSleepScheduleId=$systemDescSleepScheduleId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescSleepScheduleInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescSleepScheduleAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescSleepScheduleUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescSleepScheduleAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescSleepScheduleId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescSleepScheduleAsync",
            query = "systemDescSleepScheduleId=$systemDescSleepScheduleId"
        )
    }
}