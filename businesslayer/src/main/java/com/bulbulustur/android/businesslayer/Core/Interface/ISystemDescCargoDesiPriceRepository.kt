package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoDesiPriceDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCargoDesiPriceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoDesiPriceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescCargoDesiPriceRepository {

    suspend fun GetSystemDescCargoDesiPricesAsync(
        count: Int
    ): Result<List<SystemDescCargoDesiPriceDTO>>

    suspend fun GetSystemDescCargoDesiPriceByIdAsync(
        systemDescCargoDesiPriceId: Int
    ): Result<SystemDescCargoDesiPriceUpdateModel?>

    suspend fun GetSystemDescCargoDesiPriceByIdExtendedAsync(
        systemDescCargoDesiPriceId: Int
    ): Result<SystemDescCargoDesiPriceDTO?>

    suspend fun InsertAsync(
        model: SystemDescCargoDesiPriceInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescCargoDesiPriceUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescCargoDesiPriceId: Int
    ): Result<Unit>
}