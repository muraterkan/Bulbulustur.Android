package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelApplicationStatusHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelApplicationStatusHistoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelApplicationStatusHistoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelApplicationStatusHistoryRepository {
    suspend fun GetTravelApplicationStatusHistoriesAsync(count: Int): Result<List<TravelApplicationStatusHistoryDTO>>
    suspend fun GetTravelApplicationStatusHistoryByIdAsync(travelApplicationStatusHistoryId: Int): Result<TravelApplicationStatusHistoryUpdateModel?>
    suspend fun GetTravelApplicationStatusHistoryByIdExtendedAsync(travelApplicationStatusHistoryId: Int): Result<TravelApplicationStatusHistoryDTO?>
    suspend fun InsertAsync(model: TravelApplicationStatusHistoryInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelApplicationStatusHistoryUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelApplicationStatusHistoryId: Int): Result<Unit>
}