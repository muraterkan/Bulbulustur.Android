package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelOfferBenefitDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelOfferBenefitInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelOfferBenefitUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelOfferBenefitRepository {
    suspend fun GetTravelOfferBenefitsAsync(count: Int): Result<List<TravelOfferBenefitDTO>>
    suspend fun GetTravelOfferBenefitByIdAsync(travelOfferBenefitId: Int): Result<TravelOfferBenefitUpdateModel?>
    suspend fun GetTravelOfferBenefitByIdExtendedAsync(travelOfferBenefitId: Int): Result<TravelOfferBenefitDTO?>
    suspend fun InsertAsync(model: TravelOfferBenefitInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelOfferBenefitUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelOfferBenefitId: Int): Result<Unit>
}