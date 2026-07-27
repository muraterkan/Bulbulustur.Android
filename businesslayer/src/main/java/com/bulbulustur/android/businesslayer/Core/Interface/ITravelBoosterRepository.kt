package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelBoosterDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelBoosterInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelBoosterUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelBoosterRepository {
    suspend fun GetTravelBoostersAsync(count: Int): Result<List<TravelBoosterDTO>>
    suspend fun GetTravelBoosterByIdAsync(travelBoosterId: Int): Result<TravelBoosterUpdateModel?>
    suspend fun GetTravelBoosterByIdExtendedAsync(travelBoosterId: Int): Result<TravelBoosterDTO?>
    suspend fun InsertAsync(model: TravelBoosterInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelBoosterUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelBoosterId: Int): Result<Unit>
}