package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageLevelDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescLanguageLevelRepository {

    
suspend fun GetSystemDescLanguageLevelsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescLanguageLevelDTO>>

    suspend fun GetSystemDescLanguageLevelByIdExtendedAsync(
        languageLevelId: Int
    ): Result<SystemDescLanguageLevelDTO?>
}
