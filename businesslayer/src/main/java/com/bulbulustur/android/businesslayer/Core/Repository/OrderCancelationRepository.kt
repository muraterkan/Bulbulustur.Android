package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.OrderCancelationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IOrderCancelationRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.OrderCancelationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.OrderCancelationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class OrderCancelationRepository(
    private val apiClient: ApiClient = ApiClient
) : IOrderCancelationRepository {

    override suspend fun GetOrderCancelationListAsync(): Result<List<OrderCancelationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetOrderCancelationListAsync"
        )
    }

    override suspend fun GetOrderCancelationByIdAsync(
        orderCancelationId: Int
    ): Result<OrderCancelationUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetOrderCancelationByIdAsync",
            query = "orderCancelationId=$orderCancelationId"
        )
    }

    override suspend fun GetOrderCancelationByIdExtendedAsync(
        orderCancelationId: Int
    ): Result<OrderCancelationDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetOrderCancelationByIdExtendedAsync",
            query = "orderCancelationId=$orderCancelationId"
        )
    }

    override suspend fun InsertAsync(
        model: OrderCancelationInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: OrderCancelationUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        orderCancelationId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "orderCancelationId=$orderCancelationId"
        )
    }
}