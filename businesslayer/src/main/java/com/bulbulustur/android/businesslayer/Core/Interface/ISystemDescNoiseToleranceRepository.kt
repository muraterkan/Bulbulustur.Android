package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescNoiseToleranceDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescNoiseToleranceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescNoiseToleranceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescNoiseToleranceRepository {

    suspend fun GetSystemDescNoiseTolerancesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescNoiseToleranceDTO>>

    suspend fun GetSystemDescNoiseToleranceByIdAsync(
        systemDescNoiseToleranceId: Int
    ): Result<SystemDescNoiseToleranceUpdateModel?>

    suspend fun GetSystemDescNoiseToleranceByIdExtendedAsync(
        languageId: Int,
        systemDescNoiseToleranceId: Int
    ): Result<SystemDescNoiseToleranceDTO?>

    suspend fun InsertAsync(
        model: SystemDescNoiseToleranceInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescNoiseToleranceUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescNoiseToleranceId: Int
    ): Result<Unit>
}