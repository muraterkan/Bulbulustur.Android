package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCurrencyDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCurrencyUpdateModel

interface ISystemDescCurrencyRepository {

    suspend fun GetSystemDescCurrencyListAsync(): Result<List<SystemDescCurrencyDTO>>

    suspend fun GetSystemDescCurrencyByIdAsync(
        systemDescCurrencyId: Int
    ): Result<SystemDescCurrencyUpdateModel?>

    suspend fun GetSystemDescCurrencyByIdExtendedAsync(
        systemDescCurrencyId: Int
    ): Result<SystemDescCurrencyDTO?>
}
