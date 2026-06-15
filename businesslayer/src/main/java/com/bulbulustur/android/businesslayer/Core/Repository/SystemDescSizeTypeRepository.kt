package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSizeTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescSizeTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescSizeTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescSizeTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescSizeTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescSizeTypeRepository {

    override suspend fun GetSystemDescSizeTypeListAsync(): Result<List<SystemDescSizeTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescSizeTypeListAsync"
        )
    }

    override suspend fun GetSystemDescSizeTypeByIdAsync(
        systemDescSizeTypeId: Int
    ): Result<SystemDescSizeTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescSizeTypeByIdAsync",
            query = "systemDescSizeTypeId=$systemDescSizeTypeId"
        )
    }

    override suspend fun GetSystemDescSizeTypeByIdExtendedAsync(
        systemDescSizeTypeId: Int
    ): Result<SystemDescSizeTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescSizeTypeByIdExtendedAsync",
            query = "systemDescSizeTypeId=$systemDescSizeTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescSizeTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescSizeTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescSizeTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescSizeTypeId=$systemDescSizeTypeId"
        )
    }
}