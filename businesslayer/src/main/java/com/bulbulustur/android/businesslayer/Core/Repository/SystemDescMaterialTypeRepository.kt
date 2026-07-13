package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMaterialTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescMaterialTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescMaterialTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMaterialTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescMaterialTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescMaterialTypeRepository {

    override suspend fun GetSystemDescMaterialTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescMaterialTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "MasterData/GetSystemDescMaterialTypesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescMaterialTypeByIdAsync(
        systemDescMaterialTypeId: Int
    ): Result<SystemDescMaterialTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescMaterialTypeByIdAsync",
            query = "systemDescMaterialTypeId=$systemDescMaterialTypeId"
        )
    }

    override suspend fun GetSystemDescMaterialTypeByIdExtendedAsync(
        systemDescMaterialTypeId: Int
    ): Result<SystemDescMaterialTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescMaterialTypeByIdExtendedAsync",
            query = "systemDescMaterialTypeId=$systemDescMaterialTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescMaterialTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescMaterialTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescMaterialTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescMaterialTypeId=$systemDescMaterialTypeId"
        )
    }
}