package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageThreadDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleMessageThreadRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageThreadUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleMessageThreadRepository(
    private val apiClient: ApiClient
) : IWholesaleMessageThreadRepository {

    override suspend fun GetWholesaleMessageThreadListAsync(): Result<List<WholesaleMessageThreadDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleMessageThreadByIdAsync(
        wholesaleMessageThreadId: Int
    ): Result<WholesaleMessageThreadUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleMessageThreadByIdExtendedAsync(
        wholesaleMessageThreadId: Int
    ): Result<WholesaleMessageThreadDTO?> {
        TODO("Not implemented yet")
    }
}
