package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AdvertProductDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAdvertProductRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AdvertProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class AdvertProductRepository(
    private val apiClient: ApiClient
) : IAdvertProductRepository {

    override suspend fun GetAdvertProductListAsync(): Result<List<AdvertProductDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAdvertProductByIdAsync(
        advertProductId: Int
    ): Result<AdvertProductUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAdvertProductByIdExtendedAsync(
        advertProductId: Int
    ): Result<AdvertProductDTO?> {
        TODO("Not implemented yet")
    }
}
