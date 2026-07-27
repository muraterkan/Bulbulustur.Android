package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IPropertyRepository {

    suspend fun GetPropertiesAsync(
        count: Int
    ): Result<List<PropertyDTO>>

    suspend fun GetPropertiesByMemberIdAsync(
        memberId: Int,
        count: Int
    ): Result<List<PropertyDTO>>

    suspend fun GetPropertyByIdAsync(
        propertyId: Int
    ): Result<PropertyUpdateModel?>

    suspend fun GetPropertyByIdExtendedAsync(
        propertyId: Int
    ): Result<PropertyDTO?>

    suspend fun InsertAsync(
        model: PropertyInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: PropertyUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        propertyId: Int
    ): Result<Unit>
}