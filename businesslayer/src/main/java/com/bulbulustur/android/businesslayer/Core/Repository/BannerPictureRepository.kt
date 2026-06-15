package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.BannerPictureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IBannerPictureRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BannerPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class BannerPictureRepository(
    private val apiClient: ApiClient
) : IBannerPictureRepository {

    override suspend fun GetBannerPictureListAsync(): Result<List<BannerPictureDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetBannerPictureByIdAsync(
        bannerPictureId: Int
    ): Result<BannerPictureUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetBannerPictureByIdExtendedAsync(
        bannerPictureId: Int
    ): Result<BannerPictureDTO?> {
        TODO("Not implemented yet")
    }
}
