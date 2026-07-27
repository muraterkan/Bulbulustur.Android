package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyContactRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyContactRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyContactRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IPropertyContactRequestRepository {

    suspend fun GetPropertyContactRequestsAsync(
        count: Int
    ): Result<List<PropertyContactRequestDTO>>

    suspend fun GetPropertyContactRequestsByPropertyListingIdAsync(
        propertyListingId: Int,
        count: Int
    ): Result<List<PropertyContactRequestDTO>>

    suspend fun GetPropertyContactRequestByIdAsync(
        propertyContactRequestId: Int
    ): Result<PropertyContactRequestUpdateModel?>

    suspend fun GetPropertyContactRequestByIdExtendedAsync(
        propertyContactRequestId: Int
    ): Result<PropertyContactRequestDTO?>

    suspend fun InsertAsync(
        model: PropertyContactRequestInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: PropertyContactRequestUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        propertyContactRequestId: Int
    ): Result<Unit>
}