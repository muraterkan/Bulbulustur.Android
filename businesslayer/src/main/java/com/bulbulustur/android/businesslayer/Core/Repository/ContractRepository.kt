package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ContractDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IContractRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ContractInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ContractUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ContractRepository(
    private val apiClient: ApiClient = ApiClient
) : IContractRepository {

    override suspend fun GetContractListAsync(): Result<List<ContractDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetContractListAsync"
        )
    }

    override suspend fun GetContractByIdAsync(
        contractId: Int
    ): Result<ContractUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetContractByIdAsync",
            query = "contractId=$contractId"
        )
    }

    override suspend fun GetContractByIdExtendedAsync(
        contractId: Int
    ): Result<ContractDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetContractByIdExtendedAsync",
            query = "contractId=$contractId"
        )
    }

    override suspend fun InsertAsync(
        model: ContractInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ContractUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        contractId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "contractId=$contractId"
        )
    }
}