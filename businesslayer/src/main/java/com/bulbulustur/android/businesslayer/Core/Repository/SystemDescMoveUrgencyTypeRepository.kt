package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMoveUrgencyTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescMoveUrgencyTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescMoveUrgencyTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMoveUrgencyTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescMoveUrgencyTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescMoveUrgencyTypeRepository {

    override suspend fun GetSystemDescMoveUrgencyTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescMoveUrgencyTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescMoveUrgencyTypesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescMoveUrgencyTypeByIdAsync(
        systemDescMoveUrgencyTypeId: Int
    ): Result<SystemDescMoveUrgencyTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescMoveUrgencyTypeByIdAsync",
            query = "systemDescMoveUrgencyTypeId=$systemDescMoveUrgencyTypeId"
        )
    }

    override suspend fun GetSystemDescMoveUrgencyTypeByIdExtendedAsync(
        languageId: Int,
        systemDescMoveUrgencyTypeId: Int
    ): Result<SystemDescMoveUrgencyTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescMoveUrgencyTypeByIdExtendedAsync",
            query = "languageId=$languageId&systemDescMoveUrgencyTypeId=$systemDescMoveUrgencyTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescMoveUrgencyTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertSystemDescMoveUrgencyTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescMoveUrgencyTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdateSystemDescMoveUrgencyTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescMoveUrgencyTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeleteSystemDescMoveUrgencyTypeAsync",
            query = "systemDescMoveUrgencyTypeId=$systemDescMoveUrgencyTypeId"
        )
    }
}