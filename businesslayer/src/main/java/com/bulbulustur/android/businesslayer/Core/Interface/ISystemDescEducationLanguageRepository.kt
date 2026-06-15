package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescEducationLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescEducationLanguageUpdateModel

interface ISystemDescEducationLanguageRepository {

    suspend fun GetSystemDescEducationLanguageListAsync(): Result<List<SystemDescEducationLanguageDTO>>

    suspend fun GetSystemDescEducationLanguageByIdAsync(
        systemDescEducationLanguageId: Int
    ): Result<SystemDescEducationLanguageUpdateModel?>

    suspend fun GetSystemDescEducationLanguageByIdExtendedAsync(
        systemDescEducationLanguageId: Int
    ): Result<SystemDescEducationLanguageDTO?>
}
