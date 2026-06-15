package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescLanguageLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescLanguageLanguageUpdateModel

class SystemDescLanguageLanguageRepository : ISystemDescLanguageLanguageRepository {

    override suspend fun GetSystemDescLanguageLanguagesAsync(
        languageId: Int,
        systemDescLanguageId: Int
    ): Result<List<SystemDescLanguageLanguageDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescLanguageLanguageByIdAsync(
        systemDescLanguageLanguageId: Int
    ): Result<SystemDescLanguageLanguageUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescLanguageLanguageByIdExtendedAsync(
        systemDescLanguageLanguageId: Int
    ): Result<SystemDescLanguageLanguageDTO?> {
        TODO("Not implemented yet")
    }
}
