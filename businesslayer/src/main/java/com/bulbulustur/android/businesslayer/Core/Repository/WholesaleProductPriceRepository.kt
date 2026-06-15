package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductPriceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductPriceRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductPriceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleProductPriceRepository(
    private val apiClient: ApiClient
) : IWholesaleProductPriceRepository {

    override suspend fun GetWholesaleProductPriceListAsync(): Result<List<WholesaleProductPriceDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductPriceByIdAsync(
        productPriceId: Int
    ): Result<WholesaleProductPriceUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductPriceByIdExtendedAsync(
        productPriceId: Int
    ): Result<WholesaleProductPriceDTO?> {
        TODO("Not implemented yet")
    }
}
