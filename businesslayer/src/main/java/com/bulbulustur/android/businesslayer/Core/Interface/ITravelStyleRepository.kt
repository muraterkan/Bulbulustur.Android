package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelStyleDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelStyleInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelStyleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelStyleRepository {
    suspend fun GetTravelStylesAsync(count: Int): Result<List<TravelStyleDTO>>
    suspend fun GetTravelStyleByIdAsync(travelStyleId: Int): Result<TravelStyleUpdateModel?>
    suspend fun GetTravelStyleByIdExtendedAsync(travelStyleId: Int): Result<TravelStyleDTO?>
    suspend fun InsertAsync(model: TravelStyleInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelStyleUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelStyleId: Int): Result<Unit>
}