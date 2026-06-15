package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.BasketDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BasketUpdateModel

interface IBasketRepository {

    suspend fun GetBasketListAsync(): Result<List<BasketDTO>>

    suspend fun GetBasketByIdAsync(
        basketId: Int
    ): Result<BasketUpdateModel?>

    suspend fun GetBasketByIdExtendedAsync(
        basketId: Int
    ): Result<BasketDTO?>
}
