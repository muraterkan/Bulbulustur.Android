package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescListingStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescListingStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescListingStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescListingStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescListingStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescListingStatusRepository {

    override suspend fun GetSystemDescListingStatusesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescListingStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescListingStatusesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescListingStatusByIdAsync(
        systemDescListingStatusId: Int
    ): Result<SystemDescListingStatusUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescListingStatusByIdAsync",
            query = "systemDescListingStatusId=$systemDescListingStatusId"
        )
    }

    override suspend fun GetSystemDescListingStatusByIdExtendedAsync(
        languageId: Int,
        systemDescListingStatusId: Int
    ): Result<SystemDescListingStatusDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescListingStatusByIdExtendedAsync",
            query = "languageId=$languageId&systemDescListingStatusId=$systemDescListingStatusId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescListingStatusInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertSystemDescListingStatusAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescListingStatusUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdateSystemDescListingStatusAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescListingStatusId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeleteSystemDescListingStatusAsync",
            query = "systemDescListingStatusId=$systemDescListingStatusId"
        )
    }
}