package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCoupleAcceptanceTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCoupleAcceptanceTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCoupleAcceptanceTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCoupleAcceptanceTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescCoupleAcceptanceTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescCoupleAcceptanceTypeRepository {

    override suspend fun GetSystemDescCoupleAcceptanceTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescCoupleAcceptanceTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCoupleAcceptanceTypesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescCoupleAcceptanceTypeByIdAsync(
        systemDescCoupleAcceptanceTypeId: Int
    ): Result<SystemDescCoupleAcceptanceTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCoupleAcceptanceTypeByIdAsync",
            query = "systemDescCoupleAcceptanceTypeId=$systemDescCoupleAcceptanceTypeId"
        )
    }

    override suspend fun GetSystemDescCoupleAcceptanceTypeByIdExtendedAsync(
        languageId: Int,
        systemDescCoupleAcceptanceTypeId: Int
    ): Result<SystemDescCoupleAcceptanceTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCoupleAcceptanceTypeByIdExtendedAsync",
            query = "languageId=$languageId&systemDescCoupleAcceptanceTypeId=$systemDescCoupleAcceptanceTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescCoupleAcceptanceTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescCoupleAcceptanceTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescCoupleAcceptanceTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescCoupleAcceptanceTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescCoupleAcceptanceTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescCoupleAcceptanceTypeAsync",
            query = "systemDescCoupleAcceptanceTypeId=$systemDescCoupleAcceptanceTypeId"
        )
    }
}