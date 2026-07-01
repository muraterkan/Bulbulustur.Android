package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.BasketDTO
import com.bulbulustur.android.businesslayer.Core.DTO.BasketInsertResponse
import com.bulbulustur.android.businesslayer.Core.DTO.BasketQuantityUpdateResponse
import com.bulbulustur.android.businesslayer.Core.DTO.BasketSummaryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IBasketRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BasketInsertRequest
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BasketQuantityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class BasketRepository(
    private val apiClient: ApiClient = ApiClient
) : IBasketRepository {

    override suspend fun GetBasketsAsync(
        memberId: Int,
        count: Int
    ): Result<List<BasketDTO>> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.COMMERCE_SUPPORT_BASKET_BASE_URL,
            method =
                "GetBasketsAsync",
            query =
                "memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetBasketSummaryAsync(
        memberId: Int
    ): Result<BasketSummaryDTO> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.COMMERCE_SUPPORT_BASKET_BASE_URL,
            method =
                "GetBasketSummaryAsync",
            query =
                "memberId=$memberId"
        )
    }

    override suspend fun InsertBasketItemAsync(
        memberId: Int,
        request: BasketInsertRequest
    ): Result<BasketInsertResponse> {
        return apiClient.PostRawAsync(
            baseUrl =
                ApiRoutes.COMMERCE_SUPPORT_BASKET_BASE_URL,
            method =
                "InsertBasketItemAsync",
            data =
                request,
            query =
                "memberId=$memberId"
        )
    }

    override suspend fun UpdateBasketQuantityAsync(
        memberId: Int,
        request: BasketQuantityUpdateModel
    ): Result<BasketQuantityUpdateResponse> {
        return apiClient.PutRawAsync(
            baseUrl =
                ApiRoutes.COMMERCE_SUPPORT_BASKET_BASE_URL,
            method =
                "UpdateBasketQuantityAsync",
            data =
                request,
            query =
                "memberId=$memberId"
        )
    }

    override suspend fun DeleteBasketItemAsync(
        memberId: Int,
        basketId: Int
    ): Result<Any?> {
        return apiClient.DeleteAsync(
            baseUrl =
                ApiRoutes.COMMERCE_SUPPORT_BASKET_BASE_URL,
            method =
                "DeleteBasketItemAsync",
            query =
                "memberId=$memberId&basketId=$basketId"
        )
    }

    override suspend fun MoveBasketToFavoriteAsync(
        basketId: Int
    ): Result<Any?> {
        return apiClient.PostAsync(
            baseUrl =
                ApiRoutes.COMMERCE_SUPPORT_BASKET_BASE_URL,
            method =
                "MoveBasketToFavoriteAsync",
            data =
                Unit,
            query =
                "basketId=$basketId"
        )
    }
}