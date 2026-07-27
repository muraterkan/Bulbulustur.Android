package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescChildrenHouseholdPreferenceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescChildrenHouseholdPreferenceRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescChildrenHouseholdPreferenceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescChildrenHouseholdPreferenceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescChildrenHouseholdPreferenceRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescChildrenHouseholdPreferenceRepository {

    override suspend fun GetSystemDescChildrenHouseholdPreferencesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescChildrenHouseholdPreferenceDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescChildrenHouseholdPreferencesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescChildrenHouseholdPreferenceByIdAsync(
        systemDescChildrenHouseholdPreferenceId: Int
    ): Result<SystemDescChildrenHouseholdPreferenceUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescChildrenHouseholdPreferenceByIdAsync",
            query = "systemDescChildrenHouseholdPreferenceId=$systemDescChildrenHouseholdPreferenceId"
        )
    }

    override suspend fun GetSystemDescChildrenHouseholdPreferenceByIdExtendedAsync(
        languageId: Int,
        systemDescChildrenHouseholdPreferenceId: Int
    ): Result<SystemDescChildrenHouseholdPreferenceDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescChildrenHouseholdPreferenceByIdExtendedAsync",
            query = "languageId=$languageId&systemDescChildrenHouseholdPreferenceId=$systemDescChildrenHouseholdPreferenceId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescChildrenHouseholdPreferenceInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescChildrenHouseholdPreferenceAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescChildrenHouseholdPreferenceUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescChildrenHouseholdPreferenceAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescChildrenHouseholdPreferenceId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescChildrenHouseholdPreferenceAsync",
            query = "systemDescChildrenHouseholdPreferenceId=$systemDescChildrenHouseholdPreferenceId"
        )
    }
}