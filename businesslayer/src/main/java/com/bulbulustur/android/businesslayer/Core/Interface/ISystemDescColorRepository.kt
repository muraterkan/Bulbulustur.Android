package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescColorDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescColorUpdateModel

interface ISystemDescColorRepository {

    suspend fun GetSystemDescColorListAsync(): Result<List<SystemDescColorDTO>>

    suspend fun GetSystemDescColorByIdAsync(
        systemDescColorId: Int
    ): Result<SystemDescColorUpdateModel?>

    suspend fun GetSystemDescColorByIdExtendedAsync(
        systemDescColorId: Int
    ): Result<SystemDescColorDTO?>
}
