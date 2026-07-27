package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMatchConstraintTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescMatchConstraintTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMatchConstraintTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescMatchConstraintTypeRepository {

    suspend fun GetSystemDescMatchConstraintTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescMatchConstraintTypeDTO>>

    suspend fun GetSystemDescMatchConstraintTypeByIdAsync(
        systemDescMatchConstraintTypeId: Int
    ): Result<SystemDescMatchConstraintTypeUpdateModel?>

    suspend fun GetSystemDescMatchConstraintTypeByIdExtendedAsync(
        languageId: Int,
        systemDescMatchConstraintTypeId: Int
    ): Result<SystemDescMatchConstraintTypeDTO?>

    suspend fun InsertAsync(
        model: SystemDescMatchConstraintTypeInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescMatchConstraintTypeUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescMatchConstraintTypeId: Int
    ): Result<Unit>
}