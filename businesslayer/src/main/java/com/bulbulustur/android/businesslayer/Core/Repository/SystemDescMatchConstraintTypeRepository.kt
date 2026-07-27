package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMatchConstraintTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescMatchConstraintTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescMatchConstraintTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMatchConstraintTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescMatchConstraintTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescMatchConstraintTypeRepository {

    override suspend fun GetSystemDescMatchConstraintTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescMatchConstraintTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescMatchConstraintTypesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescMatchConstraintTypeByIdAsync(
        systemDescMatchConstraintTypeId: Int
    ): Result<SystemDescMatchConstraintTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescMatchConstraintTypeByIdAsync",
            query = "systemDescMatchConstraintTypeId=$systemDescMatchConstraintTypeId"
        )
    }

    override suspend fun GetSystemDescMatchConstraintTypeByIdExtendedAsync(
        languageId: Int,
        systemDescMatchConstraintTypeId: Int
    ): Result<SystemDescMatchConstraintTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescMatchConstraintTypeByIdExtendedAsync",
            query = "languageId=$languageId&systemDescMatchConstraintTypeId=$systemDescMatchConstraintTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescMatchConstraintTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescMatchConstraintTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescMatchConstraintTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescMatchConstraintTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescMatchConstraintTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescMatchConstraintTypeAsync",
            query = "systemDescMatchConstraintTypeId=$systemDescMatchConstraintTypeId"
        )
    }
}