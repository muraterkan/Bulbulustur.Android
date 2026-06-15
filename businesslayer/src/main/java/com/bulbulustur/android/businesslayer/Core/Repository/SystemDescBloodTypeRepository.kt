package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBloodTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescBloodTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescBloodTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescBloodTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescBloodTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescBloodTypeRepository {

    override suspend fun GetSystemDescBloodTypeListAsync(): Result<List<SystemDescBloodTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescBloodTypeListAsync"
        )
    }

    override suspend fun GetSystemDescBloodTypeByIdAsync(
        systemDescBloodTypeId: Int
    ): Result<SystemDescBloodTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescBloodTypeByIdAsync",
            query = "systemDescBloodTypeId=$systemDescBloodTypeId"
        )
    }

    override suspend fun GetSystemDescBloodTypeByIdExtendedAsync(
        systemDescBloodTypeId: Int
    ): Result<SystemDescBloodTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescBloodTypeByIdExtendedAsync",
            query = "systemDescBloodTypeId=$systemDescBloodTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescBloodTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescBloodTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescBloodTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescBloodTypeId=$systemDescBloodTypeId"
        )
    }
}