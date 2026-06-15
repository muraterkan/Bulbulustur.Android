package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CookieConsentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICookieConsentRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CookieConsentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CookieConsentRepository(
    private val apiClient: ApiClient
) : ICookieConsentRepository {

    override suspend fun GetCookieConsentListAsync(): Result<List<CookieConsentDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCookieConsentByIdAsync(
        cookieConsentId: Int
    ): Result<CookieConsentUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCookieConsentByIdExtendedAsync(
        cookieConsentId: Int
    ): Result<CookieConsentDTO?> {
        TODO("Not implemented yet")
    }
}
