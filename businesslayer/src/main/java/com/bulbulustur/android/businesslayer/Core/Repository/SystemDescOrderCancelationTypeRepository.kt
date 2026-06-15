package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderCancelationTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescOrderCancelationTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescOrderCancelationTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderCancelationTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescOrderCancelationTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescOrderCancelationTypeRepository {

    override suspend fun GetSystemDescOrderCancelationTypeListAsync(): Result<List<SystemDescOrderCancelationTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescOrderCancelationTypeListAsync"
        )
    }

    override suspend fun GetSystemDescOrderCancelationTypeByIdAsync(
        systemDescOrderCancelationTypeId: Int
    ): Result<SystemDescOrderCancelationTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescOrderCancelationTypeByIdAsync",
            query = "systemDescOrderCancelationTypeId=$systemDescOrderCancelationTypeId"
        )
    }

    override suspend fun GetSystemDescOrderCancelationTypeByIdExtendedAsync(
        systemDescOrderCancelationTypeId: Int
    ): Result<SystemDescOrderCancelationTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescOrderCancelationTypeByIdExtendedAsync",
            query = "systemDescOrderCancelationTypeId=$systemDescOrderCancelationTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescOrderCancelationTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescOrderCancelationTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescOrderCancelationTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescOrderCancelationTypeId=$systemDescOrderCancelationTypeId"
        )
    }
}