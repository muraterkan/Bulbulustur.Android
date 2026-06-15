package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCurrencyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCurrencyRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCurrencyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescCurrencyRepository(
    private val apiClient: ApiClient
) : ISystemDescCurrencyRepository {

    override suspend fun GetSystemDescCurrencyListAsync(): Result<List<SystemDescCurrencyDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescCurrencyByIdAsync(
        systemDescCurrencyId: Int
    ): Result<SystemDescCurrencyUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescCurrencyByIdExtendedAsync(
        systemDescCurrencyId: Int
    ): Result<SystemDescCurrencyDTO?> {
        TODO("Not implemented yet")
    }
}
