package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescRoomTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescRoomTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescRoomTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRoomTypeRepository {

    suspend fun GetSystemDescRoomTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescRoomTypeDTO>>

    suspend fun GetSystemDescRoomTypeByIdAsync(
        systemDescRoomTypeId: Int
    ): Result<SystemDescRoomTypeUpdateModel?>

    suspend fun GetSystemDescRoomTypeByIdExtendedAsync(
        languageId: Int,
        systemDescRoomTypeId: Int
    ): Result<SystemDescRoomTypeDTO?>

    suspend fun InsertAsync(
        model: SystemDescRoomTypeInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescRoomTypeUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescRoomTypeId: Int
    ): Result<Unit>
}