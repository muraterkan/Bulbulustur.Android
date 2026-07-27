package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBillDisciplineDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescBillDisciplineRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescBillDisciplineInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescBillDisciplineUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescBillDisciplineRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescBillDisciplineRepository {

    override suspend fun GetSystemDescBillDisciplinesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescBillDisciplineDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescBillDisciplinesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescBillDisciplineByIdAsync(
        systemDescBillDisciplineId: Int
    ): Result<SystemDescBillDisciplineUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescBillDisciplineByIdAsync",
            query = "systemDescBillDisciplineId=$systemDescBillDisciplineId"
        )
    }

    override suspend fun GetSystemDescBillDisciplineByIdExtendedAsync(
        languageId: Int,
        systemDescBillDisciplineId: Int
    ): Result<SystemDescBillDisciplineDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescBillDisciplineByIdExtendedAsync",
            query = "languageId=$languageId&systemDescBillDisciplineId=$systemDescBillDisciplineId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescBillDisciplineInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescBillDisciplineAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescBillDisciplineUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescBillDisciplineAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescBillDisciplineId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescBillDisciplineAsync",
            query = "systemDescBillDisciplineId=$systemDescBillDisciplineId"
        )
    }
}