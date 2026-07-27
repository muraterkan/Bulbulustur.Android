package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelApplicationParticipantDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelApplicationParticipantInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelApplicationParticipantUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelApplicationParticipantRepository {
    suspend fun GetTravelApplicationParticipantsAsync(count: Int): Result<List<TravelApplicationParticipantDTO>>
    suspend fun GetTravelApplicationParticipantByIdAsync(travelApplicationParticipantId: Int): Result<TravelApplicationParticipantUpdateModel?>
    suspend fun GetTravelApplicationParticipantByIdExtendedAsync(travelApplicationParticipantId: Int): Result<TravelApplicationParticipantDTO?>
    suspend fun InsertAsync(model: TravelApplicationParticipantInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelApplicationParticipantUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelApplicationParticipantId: Int): Result<Unit>
}