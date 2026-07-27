package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelReservationDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelReservationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelReservationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelReservationRepository {
    suspend fun GetTravelReservationsAsync(count: Int): Result<List<TravelReservationDTO>>
    suspend fun GetTravelReservationByIdAsync(travelReservationId: Int): Result<TravelReservationUpdateModel?>
    suspend fun GetTravelReservationByIdExtendedAsync(travelReservationId: Int): Result<TravelReservationDTO?>
    suspend fun InsertAsync(model: TravelReservationInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelReservationUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelReservationId: Int): Result<Unit>
}