package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescUnitDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescUnitRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescUnitInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescUnitUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescUnitRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescUnitRepository {

    override suspend fun GetSystemDescUnitsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescUnitDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "MasterData/GetSystemDescUnitsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescUnitByIdAsync(
        systemDescUnitId: Int
    ): Result<SystemDescUnitUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescUnitByIdAsync",
            query = "systemDescUnitId=$systemDescUnitId"
        )
    }

    override suspend fun GetSystemDescUnitByIdExtendedAsync(
        systemDescUnitId: Int
    ): Result<SystemDescUnitDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescUnitByIdExtendedAsync",
            query = "systemDescUnitId=$systemDescUnitId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescUnitInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescUnitUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescUnitId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescUnitId=$systemDescUnitId"
        )
    }
}