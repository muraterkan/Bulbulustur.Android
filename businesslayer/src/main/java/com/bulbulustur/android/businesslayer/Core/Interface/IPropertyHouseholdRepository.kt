package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyHouseholdDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyHouseholdInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyHouseholdUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IPropertyHouseholdRepository {

    suspend fun GetPropertyHouseholdsAsync(
        count: Int
    ): Result<List<PropertyHouseholdDTO>>

    suspend fun GetPropertyHouseholdsByPropertyIdAsync(
        propertyId: Int,
        count: Int
    ): Result<List<PropertyHouseholdDTO>>

    suspend fun GetPropertyHouseholdByIdAsync(
        propertyHouseholdId: Int
    ): Result<PropertyHouseholdUpdateModel?>

    suspend fun GetPropertyHouseholdByIdExtendedAsync(
        propertyHouseholdId: Int
    ): Result<PropertyHouseholdDTO?>

    suspend fun InsertAsync(
        model: PropertyHouseholdInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: PropertyHouseholdUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        propertyHouseholdId: Int
    ): Result<Unit>
}