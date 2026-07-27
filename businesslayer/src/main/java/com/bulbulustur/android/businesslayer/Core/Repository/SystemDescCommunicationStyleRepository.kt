package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCommunicationStyleDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCommunicationStyleRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCommunicationStyleInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCommunicationStyleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescCommunicationStyleRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescCommunicationStyleRepository {

    override suspend fun GetSystemDescCommunicationStylesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescCommunicationStyleDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCommunicationStylesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescCommunicationStyleByIdAsync(
        systemDescCommunicationStyleId: Int
    ): Result<SystemDescCommunicationStyleUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCommunicationStyleByIdAsync",
            query = "systemDescCommunicationStyleId=$systemDescCommunicationStyleId"
        )
    }

    override suspend fun GetSystemDescCommunicationStyleByIdExtendedAsync(
        languageId: Int,
        systemDescCommunicationStyleId: Int
    ): Result<SystemDescCommunicationStyleDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCommunicationStyleByIdExtendedAsync",
            query = "languageId=$languageId&systemDescCommunicationStyleId=$systemDescCommunicationStyleId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescCommunicationStyleInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescCommunicationStyleAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescCommunicationStyleUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescCommunicationStyleAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescCommunicationStyleId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescCommunicationStyleAsync",
            query = "systemDescCommunicationStyleId=$systemDescCommunicationStyleId"
        )
    }
}