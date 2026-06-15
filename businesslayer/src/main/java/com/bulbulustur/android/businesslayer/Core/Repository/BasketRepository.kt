package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.BasketDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IBasketRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BasketUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class BasketRepository(
    private val apiClient: ApiClient
) : IBasketRepository {

    override suspend fun GetBasketListAsync(): Result<List<BasketDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetBasketByIdAsync(
        basketId: Int
    ): Result<BasketUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetBasketByIdExtendedAsync(
        basketId: Int
    ): Result<BasketDTO?> {
        TODO("Not implemented yet")
    }
}
