package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.PropertyRoomDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PropertyRoomInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PropertyRoomUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IPropertyRoomRepository {

    suspend fun GetPropertyRoomsAsync(
        count: Int
    ): Result<List<PropertyRoomDTO>>

    suspend fun GetPropertyRoomsByPropertyIdAsync(
        propertyId: Int,
        count: Int
    ): Result<List<PropertyRoomDTO>>

    suspend fun GetPropertyRoomByIdAsync(
        propertyRoomId: Int
    ): Result<PropertyRoomUpdateModel?>

    suspend fun GetPropertyRoomByIdExtendedAsync(
        propertyRoomId: Int
    ): Result<PropertyRoomDTO?>

    suspend fun InsertAsync(
        model: PropertyRoomInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: PropertyRoomUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        propertyRoomId: Int
    ): Result<Unit>
}