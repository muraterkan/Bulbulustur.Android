package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AccommodationRequestLocationDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AccommodationRequestLocationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AccommodationRequestLocationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IAccommodationRequestLocationRepository {

    suspend fun GetAccommodationRequestLocationsAsync(
        count: Int
    ): Result<List<AccommodationRequestLocationDTO>>

    suspend fun GetAccommodationRequestLocationsByAccommodationRequestIdAsync(
        accommodationRequestId: Int,
        count: Int
    ): Result<List<AccommodationRequestLocationDTO>>

    suspend fun GetAccommodationRequestLocationByIdAsync(
        accommodationRequestLocationId: Int
    ): Result<AccommodationRequestLocationUpdateModel?>

    suspend fun GetAccommodationRequestLocationByIdExtendedAsync(
        accommodationRequestLocationId: Int
    ): Result<AccommodationRequestLocationDTO?>

    suspend fun InsertAsync(
        model: AccommodationRequestLocationInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: AccommodationRequestLocationUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        accommodationRequestLocationId: Int
    ): Result<Unit>
}