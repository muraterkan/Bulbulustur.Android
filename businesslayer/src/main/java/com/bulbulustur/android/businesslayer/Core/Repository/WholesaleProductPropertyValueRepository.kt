package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductPropertyValueDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductPropertyValueRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductPropertyValueUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleProductPropertyValueRepository(
    private val apiClient: ApiClient
) : IWholesaleProductPropertyValueRepository {

    override suspend fun GetWholesaleProductPropertyValueListAsync(): Result<List<WholesaleProductPropertyValueDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductPropertyValueByIdAsync(
        wholesaleProductPropertyValueId: Int
    ): Result<WholesaleProductPropertyValueUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleProductPropertyValueByIdExtendedAsync(
        wholesaleProductPropertyValueId: Int
    ): Result<WholesaleProductPropertyValueDTO?> {
        TODO("Not implemented yet")
    }
}
