package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoDesiDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCargoDesiRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCargoDesiInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoDesiUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescCargoDesiRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescCargoDesiRepository {

    override suspend fun GetSystemDescCargoDesisAsync(
        count: Int
    ): Result<List<SystemDescCargoDesiDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescCargoDesisAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetSystemDescCargoDesiByIdAsync(
        systemDescCargoDesiId: Int
    ): Result<SystemDescCargoDesiUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescCargoDesiByIdAsync",
            query = "systemDescCargoDesiId=$systemDescCargoDesiId"
        )
    }

    override suspend fun GetSystemDescCargoDesiByIdExtendedAsync(
        systemDescCargoDesiId: Int
    ): Result<SystemDescCargoDesiDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "GetSystemDescCargoDesiByIdExtendedAsync",
            query = "systemDescCargoDesiId=$systemDescCargoDesiId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescCargoDesiInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "InsertSystemDescCargoDesiAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescCargoDesiUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "UpdateSystemDescCargoDesiAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescCargoDesiId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "DeleteSystemDescCargoDesiAsync",
            query = "systemDescCargoDesiId=$systemDescCargoDesiId"
        )
    }
}