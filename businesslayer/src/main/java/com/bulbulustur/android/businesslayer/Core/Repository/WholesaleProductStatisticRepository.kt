package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductStatisticDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductStatisticRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductStatisticUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleProductStatisticRepository(
    private val apiClient: ApiClient
) : IWholesaleProductStatisticRepository {

    override suspend fun GetWholesaleProductStatisticListAsync(): Result<List<WholesaleProductStatisticDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductStatisticByIdAsync(
        wholesaleProductStatisticId: Int
    ): Result<WholesaleProductStatisticUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductStatisticByIdExtendedAsync(
        wholesaleProductStatisticId: Int
    ): Result<WholesaleProductStatisticDTO?> {
        TODO("Not implemented yet")
    }
}
