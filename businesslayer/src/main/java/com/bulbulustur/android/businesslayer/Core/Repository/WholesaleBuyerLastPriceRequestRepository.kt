package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBuyerLastPriceRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleBuyerLastPriceRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBuyerLastPriceRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleBuyerLastPriceRequestRepository(
    private val apiClient: ApiClient
) : IWholesaleBuyerLastPriceRequestRepository {

    override suspend fun GetWholesaleBuyerLastPriceRequestListAsync(): Result<List<WholesaleBuyerLastPriceRequestDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleBuyerLastPriceRequestByIdAsync(
        wholesaleBuyerLastPriceRequestId: Int
    ): Result<WholesaleBuyerLastPriceRequestUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleBuyerLastPriceRequestByIdExtendedAsync(
        wholesaleBuyerLastPriceRequestId: Int
    ): Result<WholesaleBuyerLastPriceRequestDTO?> {
        TODO("Not implemented yet")
    }
}
