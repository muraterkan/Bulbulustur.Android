package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescHomeSocialLevelDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescHomeSocialLevelInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescHomeSocialLevelUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescHomeSocialLevelRepository {

    suspend fun GetSystemDescHomeSocialLevelsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescHomeSocialLevelDTO>>

    suspend fun GetSystemDescHomeSocialLevelByIdAsync(
        systemDescHomeSocialLevelId: Int
    ): Result<SystemDescHomeSocialLevelUpdateModel?>

    suspend fun GetSystemDescHomeSocialLevelByIdExtendedAsync(
        languageId: Int,
        systemDescHomeSocialLevelId: Int
    ): Result<SystemDescHomeSocialLevelDTO?>

    suspend fun InsertAsync(
        model: SystemDescHomeSocialLevelInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescHomeSocialLevelUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescHomeSocialLevelId: Int
    ): Result<Unit>
}