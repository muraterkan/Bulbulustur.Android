package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescUnitDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescUnitInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescUnitUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescUnitRepository {

    suspend fun GetSystemDescUnitsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescUnitDTO>>

    suspend fun GetSystemDescUnitByIdAsync(
        systemDescUnitId: Int
    ): Result<SystemDescUnitUpdateModel?>

    suspend fun GetSystemDescUnitByIdExtendedAsync(
        systemDescUnitId: Int
    ): Result<SystemDescUnitDTO?>

    suspend fun InsertAsync(
        model: SystemDescUnitInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescUnitUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescUnitId: Int
    ): Result<Unit>
}