package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoDesiPriceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCargoDesiPriceRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoDesiPriceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescCargoDesiPriceRepository(
    private val apiClient: ApiClient
) : ISystemDescCargoDesiPriceRepository {

    override suspend fun GetSystemDescCargoDesiPriceListAsync(): Result<List<SystemDescCargoDesiPriceDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescCargoDesiPriceByIdAsync(
        systemDescCargoDesiPriceId: Int
    ): Result<SystemDescCargoDesiPriceUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescCargoDesiPriceByIdExtendedAsync(
        systemDescCargoDesiPriceId: Int
    ): Result<SystemDescCargoDesiPriceDTO?> {
        TODO("Not implemented yet")
    }
}
