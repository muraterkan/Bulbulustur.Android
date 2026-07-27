package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyListingDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyListingInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyListingUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IPropertyListingRepository {

    suspend fun GetPropertyListingsAsync(
        count: Int
    ): Result<List<PropertyListingDTO>>

    suspend fun GetPropertyListingsByMemberIdAsync(
        memberId: Int,
        count: Int
    ): Result<List<PropertyListingDTO>>

    suspend fun GetPropertyListingByIdAsync(
        propertyListingId: Int
    ): Result<PropertyListingUpdateModel?>

    suspend fun GetPropertyListingByIdExtendedAsync(
        propertyListingId: Int
    ): Result<PropertyListingDTO?>

    suspend fun InsertAsync(
        model: PropertyListingInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: PropertyListingUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        propertyListingId: Int
    ): Result<Unit>
}