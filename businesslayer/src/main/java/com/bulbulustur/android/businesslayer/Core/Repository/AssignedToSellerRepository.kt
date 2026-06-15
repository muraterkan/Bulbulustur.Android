package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AssignedToSellerDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAssignedToSellerRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AssignedToSellerUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class AssignedToSellerRepository(
    private val apiClient: ApiClient
) : IAssignedToSellerRepository {

    override suspend fun GetAssignedToSellerListAsync(): Result<List<AssignedToSellerDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAssignedToSellerByIdAsync(
        assignedToSellerId: Int
    ): Result<AssignedToSellerUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAssignedToSellerByIdExtendedAsync(
        assignedToSellerId: Int
    ): Result<AssignedToSellerDTO?> {
        TODO("Not implemented yet")
    }
}
