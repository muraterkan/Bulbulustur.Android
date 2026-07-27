package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescVisibilityTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescVisibilityTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescVisibilityTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescVisibilityTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescVisibilityTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescVisibilityTypeRepository {

    override suspend fun GetSystemDescVisibilityTypesAsync(
        count: Int
    ): Result<List<SystemDescVisibilityTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescVisibilityTypesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetSystemDescVisibilityTypeByIdAsync(
        systemDescVisibilityTypeId: Int
    ): Result<SystemDescVisibilityTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescVisibilityTypeByIdAsync",
            query = "systemDescVisibilityTypeId=$systemDescVisibilityTypeId"
        )
    }

    override suspend fun GetSystemDescVisibilityTypeByIdExtendedAsync(
        systemDescVisibilityTypeId: Int
    ): Result<SystemDescVisibilityTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescVisibilityTypeByIdExtendedAsync",
            query = "systemDescVisibilityTypeId=$systemDescVisibilityTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescVisibilityTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "InsertSystemDescVisibilityTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescVisibilityTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "UpdateSystemDescVisibilityTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescVisibilityTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "DeleteSystemDescVisibilityTypeAsync",
            query = "systemDescVisibilityTypeId=$systemDescVisibilityTypeId"
        )
    }
}