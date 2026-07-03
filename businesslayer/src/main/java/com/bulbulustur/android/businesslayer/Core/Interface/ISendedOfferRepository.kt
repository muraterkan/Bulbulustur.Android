package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SendedOfferDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SendedOfferInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SendedOfferUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISendedOfferRepository {

    suspend fun GetSendedOffersAsync(buyerRequestKey: String, count: Int = 100): Result<List<SendedOfferDTO>>

    suspend fun GetSendedOfferByIdAsync(sendedOfferId: Int): Result<SendedOfferUpdateModel?>

    suspend fun GetSendedOfferByIdExtendedAsync(sendedOfferId: Int): Result<SendedOfferDTO?>

    suspend fun InsertAsync(model: SendedOfferInsertModel): Result<Unit>

    suspend fun UpdateAsync(model: SendedOfferUpdateModel): Result<Unit>

    suspend fun DeleteAsync(model: SendedOfferUpdateModel): Result<Unit>
}