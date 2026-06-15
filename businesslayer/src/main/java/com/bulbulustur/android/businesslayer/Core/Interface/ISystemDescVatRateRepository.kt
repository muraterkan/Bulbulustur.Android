package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescVatRateDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescVatRateUpdateModel

interface ISystemDescVatRateRepository {

    suspend fun GetSystemDescVatRateListAsync(): Result<List<SystemDescVatRateDTO>>

    suspend fun GetSystemDescVatRateByIdAsync(
        systemDescVatRateId: Int
    ): Result<SystemDescVatRateUpdateModel?>

    suspend fun GetSystemDescVatRateByIdExtendedAsync(
        systemDescVatRateId: Int
    ): Result<SystemDescVatRateDTO?>
}
