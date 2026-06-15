package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoDesiPriceDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoDesiPriceUpdateModel

interface ISystemDescCargoDesiPriceRepository {

    suspend fun GetSystemDescCargoDesiPriceListAsync(): Result<List<SystemDescCargoDesiPriceDTO>>

    suspend fun GetSystemDescCargoDesiPriceByIdAsync(
        systemDescCargoDesiPriceId: Int
    ): Result<SystemDescCargoDesiPriceUpdateModel?>

    suspend fun GetSystemDescCargoDesiPriceByIdExtendedAsync(
        systemDescCargoDesiPriceId: Int
    ): Result<SystemDescCargoDesiPriceDTO?>
}
