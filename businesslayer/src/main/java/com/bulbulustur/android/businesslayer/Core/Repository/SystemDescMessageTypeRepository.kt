package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMessageTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescMessageTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescMessageTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMessageTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescMessageTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescMessageTypeRepository {

    override suspend fun GetSystemDescMessageTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescMessageTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescMessageTypesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescMessageTypeByIdAsync(
        systemDescMessageTypeId: Int
    ): Result<SystemDescMessageTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescMessageTypeByIdAsync",
            query = "systemDescMessageTypeId=$systemDescMessageTypeId"
        )
    }

    override suspend fun GetSystemDescMessageTypeByIdExtendedAsync(
        languageId: Int,
        systemDescMessageTypeId: Int
    ): Result<SystemDescMessageTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescMessageTypeByIdExtendedAsync",
            query = "languageId=$languageId&systemDescMessageTypeId=$systemDescMessageTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescMessageTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertSystemDescMessageTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescMessageTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdateSystemDescMessageTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescMessageTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeleteSystemDescMessageTypeAsync",
            query = "systemDescMessageTypeId=$systemDescMessageTypeId"
        )
    }
}