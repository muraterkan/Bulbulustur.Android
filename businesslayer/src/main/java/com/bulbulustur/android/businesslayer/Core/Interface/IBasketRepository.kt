package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.BasketDTO
import com.bulbulustur.android.businesslayer.Core.DTO.BasketInsertResponse
import com.bulbulustur.android.businesslayer.Core.DTO.BasketQuantityUpdateResponse
import com.bulbulustur.android.businesslayer.Core.DTO.BasketSummaryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BasketInsertRequest
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BasketQuantityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IBasketRepository {

    suspend fun GetBasketsAsync(
        memberId: Int,
        count: Int
    ): Result<List<BasketDTO>>

    suspend fun GetBasketSummaryAsync(
        memberId: Int
    ): Result<BasketSummaryDTO>

    suspend fun InsertBasketItemAsync(
        memberId: Int,
        request: BasketInsertRequest
    ): Result<BasketInsertResponse>

    suspend fun UpdateBasketQuantityAsync(
        memberId: Int,
        request: BasketQuantityUpdateModel
    ): Result<BasketQuantityUpdateResponse>

    suspend fun DeleteBasketItemAsync(
        memberId: Int,
        basketId: Int
    ): Result<Any?>

    suspend fun MoveBasketToFavoriteAsync(
        basketId: Int
    ): Result<Any?>
}