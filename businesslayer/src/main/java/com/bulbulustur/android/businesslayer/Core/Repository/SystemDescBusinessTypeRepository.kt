package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBusinessTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescBusinessTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescBusinessTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescBusinessTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescBusinessTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescBusinessTypeRepository {

    override suspend fun GetSystemDescBusinessTypeListAsync(): Result<List<SystemDescBusinessTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescBusinessTypeListAsync"
        )
    }

    override suspend fun GetSystemDescBusinessTypeByIdAsync(
        systemDescBusinessTypeId: Int
    ): Result<SystemDescBusinessTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescBusinessTypeByIdAsync",
            query = "systemDescBusinessTypeId=$systemDescBusinessTypeId"
        )
    }

    override suspend fun GetSystemDescBusinessTypeByIdExtendedAsync(
        systemDescBusinessTypeId: Int
    ): Result<SystemDescBusinessTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescBusinessTypeByIdExtendedAsync",
            query = "systemDescBusinessTypeId=$systemDescBusinessTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescBusinessTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescBusinessTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescBusinessTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescBusinessTypeId=$systemDescBusinessTypeId"
        )
    }
}