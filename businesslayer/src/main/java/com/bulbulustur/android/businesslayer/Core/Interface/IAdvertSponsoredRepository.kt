package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AdvertSponsoredDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AdvertSponsoredUpdateModel

interface IAdvertSponsoredRepository {

    suspend fun GetAdvertSponsoredListAsync(): Result<List<AdvertSponsoredDTO>>

    suspend fun GetAdvertSponsoredByIdAsync(
        advertSponsoredId: Int
    ): Result<AdvertSponsoredUpdateModel?>

    suspend fun GetAdvertSponsoredByIdExtendedAsync(
        advertSponsoredId: Int
    ): Result<AdvertSponsoredDTO?>
}
