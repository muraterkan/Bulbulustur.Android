package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescColorDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescColorInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescColorUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescColorRepository {

    suspend fun GetSystemDescColorsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescColorDTO>>

    suspend fun GetSystemDescColorByIdAsync(
        systemDescColorId: Int
    ): Result<SystemDescColorUpdateModel?>

    suspend fun GetSystemDescColorByIdExtendedAsync(
        systemDescColorId: Int
    ): Result<SystemDescColorDTO?>

    suspend fun InsertAsync(
        model: SystemDescColorInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescColorUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescColorId: Int
    ): Result<Unit>
}