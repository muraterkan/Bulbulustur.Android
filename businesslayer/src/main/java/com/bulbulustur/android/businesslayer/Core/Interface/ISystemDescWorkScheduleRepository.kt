package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescWorkScheduleDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescWorkScheduleInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescWorkScheduleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescWorkScheduleRepository {

    suspend fun GetSystemDescWorkSchedulesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescWorkScheduleDTO>>

    suspend fun GetSystemDescWorkScheduleByIdAsync(
        systemDescWorkScheduleId: Int
    ): Result<SystemDescWorkScheduleUpdateModel?>

    suspend fun GetSystemDescWorkScheduleByIdExtendedAsync(
        languageId: Int,
        systemDescWorkScheduleId: Int
    ): Result<SystemDescWorkScheduleDTO?>

    suspend fun InsertAsync(
        model: SystemDescWorkScheduleInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescWorkScheduleUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescWorkScheduleId: Int
    ): Result<Unit>
}