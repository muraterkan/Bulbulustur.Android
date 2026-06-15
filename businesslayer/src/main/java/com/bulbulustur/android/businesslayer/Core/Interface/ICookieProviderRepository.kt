package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CookieProviderDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CookieProviderUpdateModel

interface ICookieProviderRepository {

    suspend fun GetCookieProviderListAsync(): Result<List<CookieProviderDTO>>

    suspend fun GetCookieProviderByIdAsync(
        cookieProviderId: Int
    ): Result<CookieProviderUpdateModel?>

    suspend fun GetCookieProviderByIdExtendedAsync(
        cookieProviderId: Int
    ): Result<CookieProviderDTO?>
}
