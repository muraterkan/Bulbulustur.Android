package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SendedOfferDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISendedOfferRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SendedOfferUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SendedOfferRepository(
    private val apiClient: ApiClient
) : ISendedOfferRepository {

    override suspend fun GetSendedOfferListAsync(): Result<List<SendedOfferDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSendedOfferByIdAsync(
        sendedOfferId: Int
    ): Result<SendedOfferUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSendedOfferByIdExtendedAsync(
        sendedOfferId: Int
    ): Result<SendedOfferDTO?> {
        TODO("Not implemented yet")
    }
}
