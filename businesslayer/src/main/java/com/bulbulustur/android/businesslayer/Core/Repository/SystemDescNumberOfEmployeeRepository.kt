package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescNumberOfEmployeeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescNumberOfEmployeeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescNumberOfEmployeeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescNumberOfEmployeeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescNumberOfEmployeeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescNumberOfEmployeeRepository {

    override suspend fun GetSystemDescNumberOfEmployeeListAsync(): Result<List<SystemDescNumberOfEmployeeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescNumberOfEmployeeListAsync"
        )
    }

    override suspend fun GetSystemDescNumberOfEmployeeByIdAsync(
        systemDescNumberOfEmployeeId: Int
    ): Result<SystemDescNumberOfEmployeeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescNumberOfEmployeeByIdAsync",
            query = "systemDescNumberOfEmployeeId=$systemDescNumberOfEmployeeId"
        )
    }

    override suspend fun GetSystemDescNumberOfEmployeeByIdExtendedAsync(
        systemDescNumberOfEmployeeId: Int
    ): Result<SystemDescNumberOfEmployeeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescNumberOfEmployeeByIdExtendedAsync",
            query = "systemDescNumberOfEmployeeId=$systemDescNumberOfEmployeeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescNumberOfEmployeeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescNumberOfEmployeeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescNumberOfEmployeeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescNumberOfEmployeeId=$systemDescNumberOfEmployeeId"
        )
    }
}