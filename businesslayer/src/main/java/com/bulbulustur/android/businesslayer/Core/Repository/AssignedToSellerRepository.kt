package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AssignedToSellerDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAssignedToSellerRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AssignedToSellerInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AssignedToSellerUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AssignedToSellerRepository(
    private val apiClient: ApiClient = ApiClient
) : IAssignedToSellerRepository {

    override suspend fun GetAssignedToSellerListAsync(): Result<List<AssignedToSellerDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAssignedToSellerListAsync"
        )
    }

    override suspend fun GetAssignedToSellerByIdAsync(
        assignedToSellerId: Int
    ): Result<AssignedToSellerUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAssignedToSellerByIdAsync",
            query = "assignedToSellerId=$assignedToSellerId"
        )
    }

    override suspend fun GetAssignedToSellerByIdExtendedAsync(
        assignedToSellerId: Int
    ): Result<AssignedToSellerDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAssignedToSellerByIdExtendedAsync",
            query = "assignedToSellerId=$assignedToSellerId"
        )
    }

    override suspend fun InsertAsync(
        model: AssignedToSellerInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: AssignedToSellerUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        assignedToSellerId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "assignedToSellerId=$assignedToSellerId"
        )
    }
}