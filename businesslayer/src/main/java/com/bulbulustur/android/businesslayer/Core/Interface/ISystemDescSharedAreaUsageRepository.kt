package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSharedAreaUsageDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescSharedAreaUsageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescSharedAreaUsageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescSharedAreaUsageRepository {

    suspend fun GetSystemDescSharedAreaUsagesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescSharedAreaUsageDTO>>

    suspend fun GetSystemDescSharedAreaUsageByIdAsync(
        systemDescSharedAreaUsageId: Int
    ): Result<SystemDescSharedAreaUsageUpdateModel?>

    suspend fun GetSystemDescSharedAreaUsageByIdExtendedAsync(
        languageId: Int,
        systemDescSharedAreaUsageId: Int
    ): Result<SystemDescSharedAreaUsageDTO?>

    suspend fun InsertAsync(
        model: SystemDescSharedAreaUsageInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescSharedAreaUsageUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescSharedAreaUsageId: Int
    ): Result<Unit>
}