package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelPhotoDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelPhotoInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelPhotoUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelPhotoRepository {
    suspend fun GetTravelPhotosAsync(count: Int): Result<List<TravelPhotoDTO>>
    suspend fun GetTravelPhotoByIdAsync(travelPhotoId: Int): Result<TravelPhotoUpdateModel?>
    suspend fun GetTravelPhotoByIdExtendedAsync(travelPhotoId: Int): Result<TravelPhotoDTO?>
    suspend fun InsertAsync(model: TravelPhotoInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelPhotoUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelPhotoId: Int): Result<Unit>
}