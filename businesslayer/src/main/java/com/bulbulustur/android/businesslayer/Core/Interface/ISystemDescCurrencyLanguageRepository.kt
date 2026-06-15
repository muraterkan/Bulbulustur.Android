package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCurrencyLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCurrencyLanguageUpdateModel

interface ISystemDescCurrencyLanguageRepository {

    suspend fun GetSystemDescCurrencyLanguageListAsync(): Result<List<SystemDescCurrencyLanguageDTO>>

    suspend fun GetSystemDescCurrencyLanguageByIdAsync(
        systemDescCurrencyLanguageId: Int
    ): Result<SystemDescCurrencyLanguageUpdateModel?>

    suspend fun GetSystemDescCurrencyLanguageByIdExtendedAsync(
        systemDescCurrencyLanguageId: Int
    ): Result<SystemDescCurrencyLanguageDTO?>
}
