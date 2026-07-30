package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescComplaintTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescComplaintTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescComplaintTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescComplaintTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescComplaintTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescComplaintTypeRepository {

    override suspend fun GetSystemDescComplaintTypesAsync(
        count: Int
    ): Result<List<SystemDescComplaintTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescComplaintTypesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetSystemDescComplaintTypeByIdAsync(
        systemDescComplaintTypeId: Int
    ): Result<SystemDescComplaintTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescComplaintTypeByIdAsync",
            query = "systemDescComplaintTypeId=$systemDescComplaintTypeId"
        )
    }

    override suspend fun GetSystemDescComplaintTypeByIdExtendedAsync(
        systemDescComplaintTypeId: Int
    ): Result<SystemDescComplaintTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescComplaintTypeByIdExtendedAsync",
            query = "systemDescComplaintTypeId=$systemDescComplaintTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescComplaintTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "InsertSystemDescComplaintTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescComplaintTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "UpdateSystemDescComplaintTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescComplaintTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "DeleteSystemDescComplaintTypeAsync",
            query = "systemDescComplaintTypeId=$systemDescComplaintTypeId"
        )
    }
}