package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMemberTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescMemberTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescMemberTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMemberTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescMemberTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescMemberTypeRepository {

    override suspend fun GetSystemDescMemberTypeListAsync(): Result<List<SystemDescMemberTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescMemberTypeListAsync"
        )
    }

    override suspend fun GetSystemDescMemberTypeByIdAsync(
        systemDescMemberTypeId: Int
    ): Result<SystemDescMemberTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescMemberTypeByIdAsync",
            query = "systemDescMemberTypeId=$systemDescMemberTypeId"
        )
    }

    override suspend fun GetSystemDescMemberTypeByIdExtendedAsync(
        systemDescMemberTypeId: Int
    ): Result<SystemDescMemberTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescMemberTypeByIdExtendedAsync",
            query = "systemDescMemberTypeId=$systemDescMemberTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescMemberTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescMemberTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescMemberTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescMemberTypeId=$systemDescMemberTypeId"
        )
    }
}