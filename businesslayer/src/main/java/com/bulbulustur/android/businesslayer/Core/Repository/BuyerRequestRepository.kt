package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.BuyerRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IBuyerRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BuyerRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class BuyerRequestRepository(
    private val apiClient: ApiClient
) : IBuyerRequestRepository {

    override suspend fun GetBuyerRequestListAsync(): Result<List<BuyerRequestDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetBuyerRequestByIdAsync(
        buyerRequestId: Int
    ): Result<BuyerRequestUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetBuyerRequestByIdExtendedAsync(
        buyerRequestId: Int
    ): Result<BuyerRequestDTO?> {
        TODO("Not implemented yet")
    }
}
