package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyListingBoosterDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyListingBoosterInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyListingBoosterUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IPropertyListingBoosterRepository {

    suspend fun GetPropertyListingBoostersAsync(
        count: Int
    ): Result<List<PropertyListingBoosterDTO>>

    suspend fun GetPropertyListingBoostersByPropertyListingIdAsync(
        propertyListingId: Int,
        count: Int
    ): Result<List<PropertyListingBoosterDTO>>

    suspend fun GetPropertyListingBoosterByIdAsync(
        propertyListingBoosterId: Int
    ): Result<PropertyListingBoosterUpdateModel?>

    suspend fun GetPropertyListingBoosterByIdExtendedAsync(
        propertyListingBoosterId: Int
    ): Result<PropertyListingBoosterDTO?>

    suspend fun InsertAsync(
        model: PropertyListingBoosterInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: PropertyListingBoosterUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        propertyListingBoosterId: Int
    ): Result<Unit>
}