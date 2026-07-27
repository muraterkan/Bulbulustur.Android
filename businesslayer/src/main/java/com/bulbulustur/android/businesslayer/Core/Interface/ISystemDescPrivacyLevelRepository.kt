package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPrivacyLevelDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPrivacyLevelInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPrivacyLevelUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescPrivacyLevelRepository {

    suspend fun GetSystemDescPrivacyLevelsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescPrivacyLevelDTO>>

    suspend fun GetSystemDescPrivacyLevelByIdAsync(
        systemDescPrivacyLevelId: Int
    ): Result<SystemDescPrivacyLevelUpdateModel?>

    suspend fun GetSystemDescPrivacyLevelByIdExtendedAsync(
        languageId: Int,
        systemDescPrivacyLevelId: Int
    ): Result<SystemDescPrivacyLevelDTO?>

    suspend fun InsertAsync(
        model: SystemDescPrivacyLevelInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescPrivacyLevelUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescPrivacyLevelId: Int
    ): Result<Unit>
}