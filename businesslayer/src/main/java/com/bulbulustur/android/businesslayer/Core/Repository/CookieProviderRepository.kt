package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CookieProviderDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICookieProviderRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CookieProviderUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CookieProviderRepository(
    private val apiClient: ApiClient
) : ICookieProviderRepository {

    override suspend fun GetCookieProviderListAsync(): Result<List<CookieProviderDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCookieProviderByIdAsync(
        cookieProviderId: Int
    ): Result<CookieProviderUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCookieProviderByIdExtendedAsync(
        cookieProviderId: Int
    ): Result<CookieProviderDTO?> {
        TODO("Not implemented yet")
    }
}
