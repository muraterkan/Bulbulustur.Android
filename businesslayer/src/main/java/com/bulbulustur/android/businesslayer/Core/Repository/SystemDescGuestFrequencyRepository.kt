package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescGuestFrequencyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescGuestFrequencyRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescGuestFrequencyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescGuestFrequencyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescGuestFrequencyRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescGuestFrequencyRepository {

    override suspend fun GetSystemDescGuestFrequenciesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescGuestFrequencyDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescGuestFrequenciesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescGuestFrequencyByIdAsync(
        systemDescGuestFrequencyId: Int
    ): Result<SystemDescGuestFrequencyUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescGuestFrequencyByIdAsync",
            query = "systemDescGuestFrequencyId=$systemDescGuestFrequencyId"
        )
    }

    override suspend fun GetSystemDescGuestFrequencyByIdExtendedAsync(
        languageId: Int,
        systemDescGuestFrequencyId: Int
    ): Result<SystemDescGuestFrequencyDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescGuestFrequencyByIdExtendedAsync",
            query = "languageId=$languageId&systemDescGuestFrequencyId=$systemDescGuestFrequencyId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescGuestFrequencyInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescGuestFrequencyAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescGuestFrequencyUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescGuestFrequencyAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescGuestFrequencyId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescGuestFrequencyAsync",
            query = "systemDescGuestFrequencyId=$systemDescGuestFrequencyId"
        )
    }
}