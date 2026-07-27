package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPetHouseholdPreferenceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescPetHouseholdPreferenceRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPetHouseholdPreferenceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPetHouseholdPreferenceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescPetHouseholdPreferenceRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescPetHouseholdPreferenceRepository {

    override suspend fun GetSystemDescPetHouseholdPreferencesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescPetHouseholdPreferenceDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPetHouseholdPreferencesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescPetHouseholdPreferenceByIdAsync(
        systemDescPetHouseholdPreferenceId: Int
    ): Result<SystemDescPetHouseholdPreferenceUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPetHouseholdPreferenceByIdAsync",
            query = "systemDescPetHouseholdPreferenceId=$systemDescPetHouseholdPreferenceId"
        )
    }

    override suspend fun GetSystemDescPetHouseholdPreferenceByIdExtendedAsync(
        languageId: Int,
        systemDescPetHouseholdPreferenceId: Int
    ): Result<SystemDescPetHouseholdPreferenceDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPetHouseholdPreferenceByIdExtendedAsync",
            query = "languageId=$languageId&systemDescPetHouseholdPreferenceId=$systemDescPetHouseholdPreferenceId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescPetHouseholdPreferenceInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescPetHouseholdPreferenceAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescPetHouseholdPreferenceUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescPetHouseholdPreferenceAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescPetHouseholdPreferenceId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescPetHouseholdPreferenceAsync",
            query = "systemDescPetHouseholdPreferenceId=$systemDescPetHouseholdPreferenceId"
        )
    }
}