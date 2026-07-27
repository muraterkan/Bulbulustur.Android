package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyListingPublicationDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyListingPublicationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyListingPublicationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IPropertyListingPublicationRepository {

    suspend fun GetPropertyListingPublicationsAsync(
        count: Int
    ): Result<List<PropertyListingPublicationDTO>>

    suspend fun GetPropertyListingPublicationsByPropertyListingIdAsync(
        propertyListingId: Int,
        count: Int
    ): Result<List<PropertyListingPublicationDTO>>

    suspend fun GetPropertyListingPublicationByIdAsync(
        propertyListingPublicationId: Int
    ): Result<PropertyListingPublicationUpdateModel?>

    suspend fun GetPropertyListingPublicationByIdExtendedAsync(
        propertyListingPublicationId: Int
    ): Result<PropertyListingPublicationDTO?>

    suspend fun InsertAsync(
        model: PropertyListingPublicationInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: PropertyListingPublicationUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        propertyListingPublicationId: Int
    ): Result<Unit>
}