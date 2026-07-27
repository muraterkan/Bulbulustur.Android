package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelOfferDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelOfferInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelOfferUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelOfferRepository {
    suspend fun GetTravelOffersAsync(count: Int): Result<List<TravelOfferDTO>>
    suspend fun GetTravelOfferByIdAsync(travelOfferId: Int): Result<TravelOfferUpdateModel?>
    suspend fun GetTravelOfferByIdExtendedAsync(travelOfferId: Int): Result<TravelOfferDTO?>
    suspend fun InsertAsync(model: TravelOfferInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelOfferUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelOfferId: Int): Result<Unit>
}