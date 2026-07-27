package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelOfferStatusHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelOfferStatusHistoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelOfferStatusHistoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelOfferStatusHistoryRepository {
    suspend fun GetTravelOfferStatusHistoriesAsync(count: Int): Result<List<TravelOfferStatusHistoryDTO>>
    suspend fun GetTravelOfferStatusHistoryByIdAsync(travelOfferStatusHistoryId: Int): Result<TravelOfferStatusHistoryUpdateModel?>
    suspend fun GetTravelOfferStatusHistoryByIdExtendedAsync(travelOfferStatusHistoryId: Int): Result<TravelOfferStatusHistoryDTO?>
    suspend fun InsertAsync(model: TravelOfferStatusHistoryInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelOfferStatusHistoryUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelOfferStatusHistoryId: Int): Result<Unit>
}