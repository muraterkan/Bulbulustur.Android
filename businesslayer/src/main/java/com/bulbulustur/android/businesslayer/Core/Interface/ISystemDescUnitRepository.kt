package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescUnitDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescUnitUpdateModel

interface ISystemDescUnitRepository {

    suspend fun GetSystemDescUnitListAsync(): Result<List<SystemDescUnitDTO>>

    suspend fun GetSystemDescUnitByIdAsync(
        systemDescUnitId: Int
    ): Result<SystemDescUnitUpdateModel?>

    suspend fun GetSystemDescUnitByIdExtendedAsync(
        systemDescUnitId: Int
    ): Result<SystemDescUnitDTO?>
}
