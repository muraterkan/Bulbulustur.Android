package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AccommodationRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AccommodationRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AccommodationRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IAccommodationRequestRepository {

    suspend fun GetAccommodationRequestsAsync(
        count: Int
    ): Result<List<AccommodationRequestDTO>>

    suspend fun GetAccommodationRequestsByMemberIdAsync(
        memberId: Int,
        count: Int
    ): Result<List<AccommodationRequestDTO>>

    suspend fun GetAccommodationRequestByIdAsync(
        accommodationRequestId: Int
    ): Result<AccommodationRequestUpdateModel?>

    suspend fun GetAccommodationRequestByIdExtendedAsync(
        accommodationRequestId: Int
    ): Result<AccommodationRequestDTO?>

    suspend fun InsertAsync(
        model: AccommodationRequestInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: AccommodationRequestUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        accommodationRequestId: Int
    ): Result<Unit>
}