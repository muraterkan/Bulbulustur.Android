package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescLanguageLanguageUpdateModel

interface ISystemDescLanguageLanguageRepository {

    suspend fun GetSystemDescLanguageLanguagesAsync(
        languageId: Int,
        systemDescLanguageId: Int
    ): Result<List<SystemDescLanguageLanguageDTO>>

    suspend fun GetSystemDescLanguageLanguageByIdAsync(
        systemDescLanguageLanguageId: Int
    ): Result<SystemDescLanguageLanguageUpdateModel?>

    suspend fun GetSystemDescLanguageLanguageByIdExtendedAsync(
        systemDescLanguageLanguageId: Int
    ): Result<SystemDescLanguageLanguageDTO?>
}
