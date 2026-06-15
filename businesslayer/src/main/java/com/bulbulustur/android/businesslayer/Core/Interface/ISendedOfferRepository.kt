package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SendedOfferDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SendedOfferUpdateModel

interface ISendedOfferRepository {

    suspend fun GetSendedOfferListAsync(): Result<List<SendedOfferDTO>>

    suspend fun GetSendedOfferByIdAsync(
        sendedOfferId: Int
    ): Result<SendedOfferUpdateModel?>

    suspend fun GetSendedOfferByIdExtendedAsync(
        sendedOfferId: Int
    ): Result<SendedOfferDTO?>
}
