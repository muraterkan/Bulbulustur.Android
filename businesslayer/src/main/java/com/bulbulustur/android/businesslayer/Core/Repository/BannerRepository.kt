package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.BannerDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IBannerRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BannerUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class BannerRepository(
    private val apiClient: ApiClient
) : IBannerRepository {

    override suspend fun GetBannerListAsync(): Result<List<BannerDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetBannerByIdAsync(
        bannerId: Int
    ): Result<BannerUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetBannerByIdExtendedAsync(
        bannerId: Int
    ): Result<BannerDTO?> {
        TODO("Not implemented yet")
    }
}
