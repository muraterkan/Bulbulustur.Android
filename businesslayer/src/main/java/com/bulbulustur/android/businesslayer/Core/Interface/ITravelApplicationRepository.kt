package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelApplicationDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelApplicationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelApplicationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelApplicationRepository {
    suspend fun GetTravelApplicationsAsync(count: Int): Result<List<TravelApplicationDTO>>
    suspend fun GetTravelApplicationByIdAsync(travelApplicationId: Int): Result<TravelApplicationUpdateModel?>
    suspend fun GetTravelApplicationByIdExtendedAsync(travelApplicationId: Int): Result<TravelApplicationDTO?>
    suspend fun InsertAsync(model: TravelApplicationInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelApplicationUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelApplicationId: Int): Result<Unit>
}