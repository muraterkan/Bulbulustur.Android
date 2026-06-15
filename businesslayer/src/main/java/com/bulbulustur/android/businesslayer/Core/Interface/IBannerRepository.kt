package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.BannerDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BannerUpdateModel

interface IBannerRepository {

    suspend fun GetBannerListAsync(): Result<List<BannerDTO>>

    suspend fun GetBannerByIdAsync(
        bannerId: Int
    ): Result<BannerUpdateModel?>

    suspend fun GetBannerByIdExtendedAsync(
        bannerId: Int
    ): Result<BannerDTO?>
}
