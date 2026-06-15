package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AdvertSponsoredDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAdvertSponsoredRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AdvertSponsoredUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class AdvertSponsoredRepository(
    private val apiClient: ApiClient
) : IAdvertSponsoredRepository {

    override suspend fun GetAdvertSponsoredListAsync(): Result<List<AdvertSponsoredDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAdvertSponsoredByIdAsync(
        advertSponsoredId: Int
    ): Result<AdvertSponsoredUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAdvertSponsoredByIdExtendedAsync(
        advertSponsoredId: Int
    ): Result<AdvertSponsoredDTO?> {
        TODO("Not implemented yet")
    }
}
