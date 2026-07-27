package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCleanlinessLevelDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCleanlinessLevelInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCleanlinessLevelUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescCleanlinessLevelRepository {

    suspend fun GetSystemDescCleanlinessLevelsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescCleanlinessLevelDTO>>

    suspend fun GetSystemDescCleanlinessLevelByIdAsync(
        systemDescCleanlinessLevelId: Int
    ): Result<SystemDescCleanlinessLevelUpdateModel?>

    suspend fun GetSystemDescCleanlinessLevelByIdExtendedAsync(
        languageId: Int,
        systemDescCleanlinessLevelId: Int
    ): Result<SystemDescCleanlinessLevelDTO?>

    suspend fun InsertAsync(
        model: SystemDescCleanlinessLevelInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescCleanlinessLevelUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescCleanlinessLevelId: Int
    ): Result<Unit>
}