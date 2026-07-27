package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyPhotoDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyPhotoInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyPhotoUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IPropertyPhotoRepository {

    suspend fun GetPropertyPhotosAsync(
        count: Int
    ): Result<List<PropertyPhotoDTO>>

    suspend fun GetPropertyPhotosByPropertyIdAsync(
        propertyId: Int,
        count: Int
    ): Result<List<PropertyPhotoDTO>>

    suspend fun GetPropertyPhotoByIdAsync(
        propertyPhotoId: Int
    ): Result<PropertyPhotoUpdateModel?>

    suspend fun GetPropertyPhotoByIdExtendedAsync(
        propertyPhotoId: Int
    ): Result<PropertyPhotoDTO?>

    suspend fun InsertAsync(
        model: PropertyPhotoInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: PropertyPhotoUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        propertyPhotoId: Int
    ): Result<Unit>
}