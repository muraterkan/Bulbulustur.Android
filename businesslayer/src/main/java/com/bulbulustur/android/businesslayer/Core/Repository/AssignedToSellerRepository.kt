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

    override suspend fun GetAssignedToSellersAsync(assignedMemberId: Int): Result<List<AssignedToSellerDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "AssignedToSeller/GetAssignedToSellersAsync",
            query = "assignedMemberId=$assignedMemberId"
        )
    }

    override suspend fun GetAssignedToSellersByIdAsync(assignedToSellerId: Int): Result<AssignedToSellerUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "AssignedToSeller/GetAssignedToSellersByIdAsync",
            query = "assignedToSellerId=$assignedToSellerId"
        )
    }

    override suspend fun GetAssignedToSellersByIdExtendedAsync(assignedToSellerId: Int): Result<AssignedToSellerDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "AssignedToSeller/GetAssignedToSellersByIdExtendedAsync",
            query = "assignedToSellerId=$assignedToSellerId"
        )
    }

    override suspend fun InsertAsync(model: AssignedToSellerInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "AssignedToSeller/AssignedToSellerInsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: AssignedToSellerUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "AssignedToSeller/AssignedToSellerUpdateAsync",
            data = model
        )
    }
}