package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescHouseholdTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescHouseholdTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescHouseholdTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescHouseholdTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescHouseholdTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescHouseholdTypeRepository {

    override suspend fun GetSystemDescHouseholdTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescHouseholdTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescHouseholdTypesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescHouseholdTypeByIdAsync(
        systemDescHouseholdTypeId: Int
    ): Result<SystemDescHouseholdTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescHouseholdTypeByIdAsync",
            query = "systemDescHouseholdTypeId=$systemDescHouseholdTypeId"
        )
    }

    override suspend fun GetSystemDescHouseholdTypeByIdExtendedAsync(
        languageId: Int,
        systemDescHouseholdTypeId: Int
    ): Result<SystemDescHouseholdTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescHouseholdTypeByIdExtendedAsync",
            query = "languageId=$languageId&systemDescHouseholdTypeId=$systemDescHouseholdTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescHouseholdTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescHouseholdTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescHouseholdTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescHouseholdTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescHouseholdTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescHouseholdTypeAsync",
            query = "systemDescHouseholdTypeId=$systemDescHouseholdTypeId"
        )
    }
}