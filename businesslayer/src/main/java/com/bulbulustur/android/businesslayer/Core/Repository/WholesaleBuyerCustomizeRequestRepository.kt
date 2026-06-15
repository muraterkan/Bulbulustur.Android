package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBuyerCustomizeRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleBuyerCustomizeRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBuyerCustomizeRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleBuyerCustomizeRequestRepository(
    private val apiClient: ApiClient
) : IWholesaleBuyerCustomizeRequestRepository {

    override suspend fun GetWholesaleBuyerCustomizeRequestListAsync(): Result<List<WholesaleBuyerCustomizeRequestDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleBuyerCustomizeRequestByIdAsync(
        wholesaleBuyerCustomizeRequestId: Int
    ): Result<WholesaleBuyerCustomizeRequestUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleBuyerCustomizeRequestByIdExtendedAsync(
        wholesaleBuyerCustomizeRequestId: Int
    ): Result<WholesaleBuyerCustomizeRequestDTO?> {
        TODO("Not implemented yet")
    }
}
