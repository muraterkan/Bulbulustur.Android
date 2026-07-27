package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelRepository {
    suspend fun GetTravelsAsync(count: Int): Result<List<TravelDTO>>
    suspend fun GetTravelByIdAsync(travelId: Int): Result<TravelUpdateModel?>
    suspend fun GetTravelByIdExtendedAsync(travelId: Int): Result<TravelDTO?>
    suspend fun InsertAsync(model: TravelInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelId: Int): Result<Unit>
}