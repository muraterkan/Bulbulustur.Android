package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleProductRepository(
    private val apiClient: ApiClient
) : IWholesaleProductRepository {

    override suspend fun GetWholesaleProductListAsync(): Result<List<WholesaleProductDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductByIdAsync(
        wholesaleProductId: Int
    ): Result<WholesaleProductUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductByIdExtendedAsync(
        wholesaleProductId: Int
    ): Result<WholesaleProductDTO?> {
        TODO("Not implemented yet")
    }
}
