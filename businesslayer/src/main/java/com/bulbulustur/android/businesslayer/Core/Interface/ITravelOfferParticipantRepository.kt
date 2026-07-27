package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelOfferParticipantDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelOfferParticipantInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelOfferParticipantUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelOfferParticipantRepository {
    suspend fun GetTravelOfferParticipantsAsync(count: Int): Result<List<TravelOfferParticipantDTO>>
    suspend fun GetTravelOfferParticipantByIdAsync(travelOfferParticipantId: Int): Result<TravelOfferParticipantUpdateModel?>
    suspend fun GetTravelOfferParticipantByIdExtendedAsync(travelOfferParticipantId: Int): Result<TravelOfferParticipantDTO?>
    suspend fun InsertAsync(model: TravelOfferParticipantInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelOfferParticipantUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelOfferParticipantId: Int): Result<Unit>
}