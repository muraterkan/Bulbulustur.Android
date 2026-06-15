package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductRelatedDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductRelatedRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductRelatedUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleProductRelatedRepository(
    private val apiClient: ApiClient
) : IWholesaleProductRelatedRepository {

    override suspend fun GetWholesaleProductRelatedListAsync(): Result<List<WholesaleProductRelatedDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductRelatedByIdAsync(
        wholesaleProductRelatedId: Int
    ): Result<WholesaleProductRelatedUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductRelatedByIdExtendedAsync(
        wholesaleProductRelatedId: Int
    ): Result<WholesaleProductRelatedDTO?> {
        TODO("Not implemented yet")
    }
}
