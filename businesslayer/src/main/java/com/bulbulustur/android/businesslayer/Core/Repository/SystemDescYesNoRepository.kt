package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescYesNoDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescYesNoRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescYesNoInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescYesNoUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescYesNoRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescYesNoRepository {

    override suspend fun GetSystemDescYesNoListAsync(): Result<List<SystemDescYesNoDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescYesNoListAsync"
        )
    }

    override suspend fun GetSystemDescYesNoByIdAsync(
        systemDescYesNoId: Int
    ): Result<SystemDescYesNoUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescYesNoByIdAsync",
            query = "systemDescYesNoId=$systemDescYesNoId"
        )
    }

    override suspend fun GetSystemDescYesNoByIdExtendedAsync(
        systemDescYesNoId: Int
    ): Result<SystemDescYesNoDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescYesNoByIdExtendedAsync",
            query = "systemDescYesNoId=$systemDescYesNoId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescYesNoInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescYesNoUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescYesNoId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescYesNoId=$systemDescYesNoId"
        )
    }
}