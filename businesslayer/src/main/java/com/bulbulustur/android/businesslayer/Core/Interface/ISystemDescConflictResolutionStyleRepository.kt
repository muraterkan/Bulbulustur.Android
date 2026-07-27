package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescConflictResolutionStyleDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescConflictResolutionStyleInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescConflictResolutionStyleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescConflictResolutionStyleRepository {

    suspend fun GetSystemDescConflictResolutionStylesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescConflictResolutionStyleDTO>>

    suspend fun GetSystemDescConflictResolutionStyleByIdAsync(
        systemDescConflictResolutionStyleId: Int
    ): Result<SystemDescConflictResolutionStyleUpdateModel?>

    suspend fun GetSystemDescConflictResolutionStyleByIdExtendedAsync(
        languageId: Int,
        systemDescConflictResolutionStyleId: Int
    ): Result<SystemDescConflictResolutionStyleDTO?>

    suspend fun InsertAsync(
        model: SystemDescConflictResolutionStyleInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescConflictResolutionStyleUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescConflictResolutionStyleId: Int
    ): Result<Unit>
}