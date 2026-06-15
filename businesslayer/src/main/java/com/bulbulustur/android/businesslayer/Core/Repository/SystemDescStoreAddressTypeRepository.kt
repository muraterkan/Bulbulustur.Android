package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescStoreAddressTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescStoreAddressTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescStoreAddressTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescStoreAddressTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescStoreAddressTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescStoreAddressTypeRepository {

    override suspend fun GetSystemDescStoreAddressTypeListAsync(): Result<List<SystemDescStoreAddressTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescStoreAddressTypeListAsync"
        )
    }

    override suspend fun GetSystemDescStoreAddressTypeByIdAsync(
        systemDescStoreAddressTypeId: Int
    ): Result<SystemDescStoreAddressTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescStoreAddressTypeByIdAsync",
            query = "systemDescStoreAddressTypeId=$systemDescStoreAddressTypeId"
        )
    }

    override suspend fun GetSystemDescStoreAddressTypeByIdExtendedAsync(
        systemDescStoreAddressTypeId: Int
    ): Result<SystemDescStoreAddressTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescStoreAddressTypeByIdExtendedAsync",
            query = "systemDescStoreAddressTypeId=$systemDescStoreAddressTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescStoreAddressTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescStoreAddressTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescStoreAddressTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescStoreAddressTypeId=$systemDescStoreAddressTypeId"
        )
    }
}