package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.BannerPictureDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BannerPictureUpdateModel

interface IBannerPictureRepository {

    suspend fun GetBannerPictureListAsync(): Result<List<BannerPictureDTO>>

    suspend fun GetBannerPictureByIdAsync(
        bannerPictureId: Int
    ): Result<BannerPictureUpdateModel?>

    suspend fun GetBannerPictureByIdExtendedAsync(
        bannerPictureId: Int
    ): Result<BannerPictureDTO?>
}
