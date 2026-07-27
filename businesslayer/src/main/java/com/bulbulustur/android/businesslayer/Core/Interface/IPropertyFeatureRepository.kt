package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyFeatureDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyFeatureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyFeatureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IPropertyFeatureRepository {

    suspend fun GetPropertyFeaturesAsync(
        count: Int
    ): Result<List<PropertyFeatureDTO>>

    suspend fun GetPropertyFeaturesByPropertyIdAsync(
        propertyId: Int,
        count: Int
    ): Result<List<PropertyFeatureDTO>>

    suspend fun GetPropertyFeatureByIdAsync(
        propertyFeatureId: Int
    ): Result<PropertyFeatureUpdateModel?>

    suspend fun GetPropertyFeatureByIdExtendedAsync(
        propertyFeatureId: Int
    ): Result<PropertyFeatureDTO?>

    suspend fun InsertAsync(
        model: PropertyFeatureInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: PropertyFeatureUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        propertyFeatureId: Int
    ): Result<Unit>
}