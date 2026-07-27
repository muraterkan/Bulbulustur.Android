package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelAcceptedApplicantTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelAcceptedApplicantTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelAcceptedApplicantTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelAcceptedApplicantTypeRepository {
    suspend fun GetTravelAcceptedApplicantTypesAsync(count: Int): Result<List<TravelAcceptedApplicantTypeDTO>>
    suspend fun GetTravelAcceptedApplicantTypeByIdAsync(travelAcceptedApplicantTypeId: Int): Result<TravelAcceptedApplicantTypeUpdateModel?>
    suspend fun GetTravelAcceptedApplicantTypeByIdExtendedAsync(travelAcceptedApplicantTypeId: Int): Result<TravelAcceptedApplicantTypeDTO?>
    suspend fun InsertAsync(model: TravelAcceptedApplicantTypeInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelAcceptedApplicantTypeUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelAcceptedApplicantTypeId: Int): Result<Unit>
}