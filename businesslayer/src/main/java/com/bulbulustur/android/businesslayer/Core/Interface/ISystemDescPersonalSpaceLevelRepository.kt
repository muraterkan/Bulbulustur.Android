package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPersonalSpaceLevelDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPersonalSpaceLevelInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPersonalSpaceLevelUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescPersonalSpaceLevelRepository {

    suspend fun GetSystemDescPersonalSpaceLevelsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescPersonalSpaceLevelDTO>>

    suspend fun GetSystemDescPersonalSpaceLevelByIdAsync(
        systemDescPersonalSpaceLevelId: Int
    ): Result<SystemDescPersonalSpaceLevelUpdateModel?>

    suspend fun GetSystemDescPersonalSpaceLevelByIdExtendedAsync(
        languageId: Int,
        systemDescPersonalSpaceLevelId: Int
    ): Result<SystemDescPersonalSpaceLevelDTO?>

    suspend fun InsertAsync(
        model: SystemDescPersonalSpaceLevelInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescPersonalSpaceLevelUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescPersonalSpaceLevelId: Int
    ): Result<Unit>
}