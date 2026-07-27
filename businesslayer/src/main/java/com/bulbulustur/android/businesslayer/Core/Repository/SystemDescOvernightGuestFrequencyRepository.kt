package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOvernightGuestFrequencyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescOvernightGuestFrequencyRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescOvernightGuestFrequencyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOvernightGuestFrequencyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescOvernightGuestFrequencyRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescOvernightGuestFrequencyRepository {

    override suspend fun GetSystemDescOvernightGuestFrequenciesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescOvernightGuestFrequencyDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescOvernightGuestFrequenciesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescOvernightGuestFrequencyByIdAsync(
        systemDescOvernightGuestFrequencyId: Int
    ): Result<SystemDescOvernightGuestFrequencyUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescOvernightGuestFrequencyByIdAsync",
            query = "systemDescOvernightGuestFrequencyId=$systemDescOvernightGuestFrequencyId"
        )
    }

    override suspend fun GetSystemDescOvernightGuestFrequencyByIdExtendedAsync(
        languageId: Int,
        systemDescOvernightGuestFrequencyId: Int
    ): Result<SystemDescOvernightGuestFrequencyDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescOvernightGuestFrequencyByIdExtendedAsync",
            query = "languageId=$languageId&systemDescOvernightGuestFrequencyId=$systemDescOvernightGuestFrequencyId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescOvernightGuestFrequencyInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescOvernightGuestFrequencyAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescOvernightGuestFrequencyUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescOvernightGuestFrequencyAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescOvernightGuestFrequencyId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescOvernightGuestFrequencyAsync",
            query = "systemDescOvernightGuestFrequencyId=$systemDescOvernightGuestFrequencyId"
        )
    }
}