package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescLanguageUpdateModel

interface ISystemDescLanguageRepository {

    suspend fun GetSystemDescLanguageListAsync(): Result<List<SystemDescLanguageDTO>>

    suspend fun GetSystemDescLanguageByIdAsync(
        systemDescLanguageId: Int
    ): Result<SystemDescLanguageUpdateModel?>

    suspend fun GetSystemDescLanguageByIdExtendedAsync(
        systemDescLanguageId: Int
    ): Result<SystemDescLanguageDTO?>
}
