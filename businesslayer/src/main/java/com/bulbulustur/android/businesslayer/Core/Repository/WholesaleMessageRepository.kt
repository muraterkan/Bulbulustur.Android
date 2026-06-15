package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleMessageRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleMessageRepository(
    private val apiClient: ApiClient
) : IWholesaleMessageRepository {

    override suspend fun GetWholesaleMessageListAsync(): Result<List<WholesaleMessageDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleMessageIdByIdAsync(
        wholesaleMessageId: Int
    ): Result<WholesaleMessageUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleMessageIdByIdExtendedAsync(
        wholesaleMessageId: Int
    ): Result<WholesaleMessageDTO?> {
        TODO("Not implemented yet")
    }
}
