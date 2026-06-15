package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCurrencyLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCurrencyLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCurrencyLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescCurrencyLanguageRepository(
    private val apiClient: ApiClient
) : ISystemDescCurrencyLanguageRepository {

    override suspend fun GetSystemDescCurrencyLanguageListAsync(): Result<List<SystemDescCurrencyLanguageDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescCurrencyLanguageByIdAsync(
        systemDescCurrencyLanguageId: Int
    ): Result<SystemDescCurrencyLanguageUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescCurrencyLanguageByIdExtendedAsync(
        systemDescCurrencyLanguageId: Int
    ): Result<SystemDescCurrencyLanguageDTO?> {
        TODO("Not implemented yet")
    }
}
