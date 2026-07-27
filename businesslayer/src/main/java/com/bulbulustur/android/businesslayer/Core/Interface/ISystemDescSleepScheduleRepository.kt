package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSleepScheduleDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescSleepScheduleInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescSleepScheduleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescSleepScheduleRepository {

    suspend fun GetSystemDescSleepSchedulesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescSleepScheduleDTO>>

    suspend fun GetSystemDescSleepScheduleByIdAsync(
        systemDescSleepScheduleId: Int
    ): Result<SystemDescSleepScheduleUpdateModel?>

    suspend fun GetSystemDescSleepScheduleByIdExtendedAsync(
        languageId: Int,
        systemDescSleepScheduleId: Int
    ): Result<SystemDescSleepScheduleDTO?>

    suspend fun InsertAsync(
        model: SystemDescSleepScheduleInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescSleepScheduleUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescSleepScheduleId: Int
    ): Result<Unit>
}