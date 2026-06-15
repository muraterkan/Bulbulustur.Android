package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPhoneTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescPhoneTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPhoneTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPhoneTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescPhoneTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescPhoneTypeRepository {

    override suspend fun GetSystemDescPhoneTypeListAsync(): Result<List<SystemDescPhoneTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPhoneTypeListAsync"
        )
    }

    override suspend fun GetSystemDescPhoneTypeByIdAsync(
        systemDescPhoneTypeId: Int
    ): Result<SystemDescPhoneTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPhoneTypeByIdAsync",
            query = "systemDescPhoneTypeId=$systemDescPhoneTypeId"
        )
    }

    override suspend fun GetSystemDescPhoneTypeByIdExtendedAsync(
        systemDescPhoneTypeId: Int
    ): Result<SystemDescPhoneTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPhoneTypeByIdExtendedAsync",
            query = "systemDescPhoneTypeId=$systemDescPhoneTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescPhoneTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescPhoneTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescPhoneTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescPhoneTypeId=$systemDescPhoneTypeId"
        )
    }
}