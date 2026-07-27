package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPropertyFeatureTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescPropertyFeatureTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPropertyFeatureTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPropertyFeatureTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescPropertyFeatureTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescPropertyFeatureTypeRepository {

    override suspend fun GetSystemDescPropertyFeatureTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescPropertyFeatureTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescPropertyFeatureTypesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescPropertyFeatureTypeByIdAsync(
        systemDescPropertyFeatureTypeId: Int
    ): Result<SystemDescPropertyFeatureTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescPropertyFeatureTypeByIdAsync",
            query = "systemDescPropertyFeatureTypeId=$systemDescPropertyFeatureTypeId"
        )
    }

    override suspend fun GetSystemDescPropertyFeatureTypeByIdExtendedAsync(
        languageId: Int,
        systemDescPropertyFeatureTypeId: Int
    ): Result<SystemDescPropertyFeatureTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescPropertyFeatureTypeByIdExtendedAsync",
            query = "languageId=$languageId&systemDescPropertyFeatureTypeId=$systemDescPropertyFeatureTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescPropertyFeatureTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertSystemDescPropertyFeatureTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescPropertyFeatureTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdateSystemDescPropertyFeatureTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescPropertyFeatureTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeleteSystemDescPropertyFeatureTypeAsync",
            query = "systemDescPropertyFeatureTypeId=$systemDescPropertyFeatureTypeId"
        )
    }
}