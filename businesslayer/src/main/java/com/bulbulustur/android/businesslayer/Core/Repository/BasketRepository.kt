package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.BasketDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IBasketRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BasketInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BasketUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class BasketRepository(
    private val apiClient: ApiClient = ApiClient
) : IBasketRepository {

    override suspend fun GetBasketListAsync(): Result<List<BasketDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBasketListAsync"
        )
    }

    override suspend fun GetBasketByIdAsync(
        basketId: Int
    ): Result<BasketUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBasketByIdAsync",
            query = "basketId=$basketId"
        )
    }

    override suspend fun GetBasketByIdExtendedAsync(
        basketId: Int
    ): Result<BasketDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBasketByIdExtendedAsync",
            query = "basketId=$basketId"
        )
    }

    override suspend fun InsertAsync(
        model: BasketInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: BasketUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        basketId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "basketId=$basketId"
        )
    }
}