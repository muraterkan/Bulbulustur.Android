package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLgbtqEnvironmentTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescLgbtqEnvironmentTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescLgbtqEnvironmentTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescLgbtqEnvironmentTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescLgbtqEnvironmentTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescLgbtqEnvironmentTypeRepository {

    override suspend fun GetSystemDescLgbtqEnvironmentTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescLgbtqEnvironmentTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescLgbtqEnvironmentTypesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescLgbtqEnvironmentTypeByIdAsync(
        systemDescLgbtqEnvironmentTypeId: Int
    ): Result<SystemDescLgbtqEnvironmentTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescLgbtqEnvironmentTypeByIdAsync",
            query = "systemDescLgbtqEnvironmentTypeId=$systemDescLgbtqEnvironmentTypeId"
        )
    }

    override suspend fun GetSystemDescLgbtqEnvironmentTypeByIdExtendedAsync(
        languageId: Int,
        systemDescLgbtqEnvironmentTypeId: Int
    ): Result<SystemDescLgbtqEnvironmentTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescLgbtqEnvironmentTypeByIdExtendedAsync",
            query = "languageId=$languageId&systemDescLgbtqEnvironmentTypeId=$systemDescLgbtqEnvironmentTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescLgbtqEnvironmentTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescLgbtqEnvironmentTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescLgbtqEnvironmentTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescLgbtqEnvironmentTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescLgbtqEnvironmentTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescLgbtqEnvironmentTypeAsync",
            query = "systemDescLgbtqEnvironmentTypeId=$systemDescLgbtqEnvironmentTypeId"
        )
    }
}