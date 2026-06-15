package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CookieConsentDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CookieConsentUpdateModel

interface ICookieConsentRepository {

    suspend fun GetCookieConsentListAsync(): Result<List<CookieConsentDTO>>

    suspend fun GetCookieConsentByIdAsync(
        cookieConsentId: Int
    ): Result<CookieConsentUpdateModel?>

    suspend fun GetCookieConsentByIdExtendedAsync(
        cookieConsentId: Int
    ): Result<CookieConsentDTO?>
}
