package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAccountActivityTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescAccountActivityTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescAccountActivityTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescAccountActivityTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescAccountActivityTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescAccountActivityTypeRepository {

    override suspend fun GetSystemDescAccountActivityTypeListAsync(): Result<List<SystemDescAccountActivityTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescAccountActivityTypeListAsync"
        )
    }

    override suspend fun GetSystemDescAccountActivityTypeByIdAsync(
        systemDescAccountActivityTypeId: Int
    ): Result<SystemDescAccountActivityTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescAccountActivityTypeByIdAsync",
            query = "systemDescAccountActivityTypeId=$systemDescAccountActivityTypeId"
        )
    }

    override suspend fun GetSystemDescAccountActivityTypeByIdExtendedAsync(
        systemDescAccountActivityTypeId: Int
    ): Result<SystemDescAccountActivityTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescAccountActivityTypeByIdExtendedAsync",
            query = "systemDescAccountActivityTypeId=$systemDescAccountActivityTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescAccountActivityTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescAccountActivityTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescAccountActivityTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescAccountActivityTypeId=$systemDescAccountActivityTypeId"
        )
    }
}