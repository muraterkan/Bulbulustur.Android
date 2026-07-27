package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMoveUrgencyTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescMoveUrgencyTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMoveUrgencyTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescMoveUrgencyTypeRepository {

    suspend fun GetSystemDescMoveUrgencyTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescMoveUrgencyTypeDTO>>

    suspend fun GetSystemDescMoveUrgencyTypeByIdAsync(
        systemDescMoveUrgencyTypeId: Int
    ): Result<SystemDescMoveUrgencyTypeUpdateModel?>

    suspend fun GetSystemDescMoveUrgencyTypeByIdExtendedAsync(
        languageId: Int,
        systemDescMoveUrgencyTypeId: Int
    ): Result<SystemDescMoveUrgencyTypeDTO?>

    suspend fun InsertAsync(
        model: SystemDescMoveUrgencyTypeInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescMoveUrgencyTypeUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescMoveUrgencyTypeId: Int
    ): Result<Unit>
}