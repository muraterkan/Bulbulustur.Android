package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPartyFrequencyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescPartyFrequencyRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPartyFrequencyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPartyFrequencyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescPartyFrequencyRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescPartyFrequencyRepository {

    override suspend fun GetSystemDescPartyFrequenciesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescPartyFrequencyDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPartyFrequenciesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescPartyFrequencyByIdAsync(
        systemDescPartyFrequencyId: Int
    ): Result<SystemDescPartyFrequencyUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPartyFrequencyByIdAsync",
            query = "systemDescPartyFrequencyId=$systemDescPartyFrequencyId"
        )
    }

    override suspend fun GetSystemDescPartyFrequencyByIdExtendedAsync(
        languageId: Int,
        systemDescPartyFrequencyId: Int
    ): Result<SystemDescPartyFrequencyDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPartyFrequencyByIdExtendedAsync",
            query = "languageId=$languageId&systemDescPartyFrequencyId=$systemDescPartyFrequencyId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescPartyFrequencyInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescPartyFrequencyAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescPartyFrequencyUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescPartyFrequencyAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescPartyFrequencyId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescPartyFrequencyAsync",
            query = "systemDescPartyFrequencyId=$systemDescPartyFrequencyId"
        )
    }
}