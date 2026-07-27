package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescHomeUsageStyleDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescHomeUsageStyleInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescHomeUsageStyleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescHomeUsageStyleRepository {

    suspend fun GetSystemDescHomeUsageStylesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescHomeUsageStyleDTO>>

    suspend fun GetSystemDescHomeUsageStyleByIdAsync(
        systemDescHomeUsageStyleId: Int
    ): Result<SystemDescHomeUsageStyleUpdateModel?>

    suspend fun GetSystemDescHomeUsageStyleByIdExtendedAsync(
        languageId: Int,
        systemDescHomeUsageStyleId: Int
    ): Result<SystemDescHomeUsageStyleDTO?>

    suspend fun InsertAsync(
        model: SystemDescHomeUsageStyleInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescHomeUsageStyleUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescHomeUsageStyleId: Int
    ): Result<Unit>
}