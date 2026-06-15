package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescVerificationTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescVerificationTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescVerificationTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescVerificationTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescVerificationTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescVerificationTypeRepository {

    override suspend fun GetSystemDescVerificationTypeListAsync(): Result<List<SystemDescVerificationTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescVerificationTypeListAsync"
        )
    }

    override suspend fun GetSystemDescVerificationTypeByIdAsync(
        systemDescVerificationTypeId: Int
    ): Result<SystemDescVerificationTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescVerificationTypeByIdAsync",
            query = "systemDescVerificationTypeId=$systemDescVerificationTypeId"
        )
    }

    override suspend fun GetSystemDescVerificationTypeByIdExtendedAsync(
        systemDescVerificationTypeId: Int
    ): Result<SystemDescVerificationTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescVerificationTypeByIdExtendedAsync",
            query = "systemDescVerificationTypeId=$systemDescVerificationTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescVerificationTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescVerificationTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescVerificationTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescVerificationTypeId=$systemDescVerificationTypeId"
        )
    }
}