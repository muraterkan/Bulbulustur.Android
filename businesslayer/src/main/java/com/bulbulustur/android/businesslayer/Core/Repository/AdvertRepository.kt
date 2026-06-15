package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AdvertDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAdvertRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AdvertUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class AdvertRepository(
    private val apiClient: ApiClient
) : IAdvertRepository {

    override suspend fun GetAdvertListAsync(): Result<List<AdvertDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAdvertByIdAsync(
        advertId: Int
    ): Result<AdvertUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAdvertByIdExtendedAsync(
        advertId: Int
    ): Result<AdvertDTO?> {
        TODO("Not implemented yet")
    }
}
