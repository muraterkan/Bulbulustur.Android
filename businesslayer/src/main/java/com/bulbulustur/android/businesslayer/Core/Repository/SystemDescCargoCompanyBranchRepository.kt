package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoCompanyBranchDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCargoCompanyBranchRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCargoCompanyBranchInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoCompanyBranchUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescCargoCompanyBranchRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescCargoCompanyBranchRepository {

    override suspend fun GetSystemDescCargoCompanyBranchListAsync(): Result<List<SystemDescCargoCompanyBranchDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCargoCompanyBranchListAsync"
        )
    }

    override suspend fun GetSystemDescCargoCompanyBranchByIdAsync(
        systemDescCargoCompanyBranchId: Int
    ): Result<SystemDescCargoCompanyBranchUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCargoCompanyBranchByIdAsync",
            query = "systemDescCargoCompanyBranchId=$systemDescCargoCompanyBranchId"
        )
    }

    override suspend fun GetSystemDescCargoCompanyBranchByIdExtendedAsync(
        systemDescCargoCompanyBranchId: Int
    ): Result<SystemDescCargoCompanyBranchDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCargoCompanyBranchByIdExtendedAsync",
            query = "systemDescCargoCompanyBranchId=$systemDescCargoCompanyBranchId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescCargoCompanyBranchInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescCargoCompanyBranchUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescCargoCompanyBranchId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescCargoCompanyBranchId=$systemDescCargoCompanyBranchId"
        )
    }
}