package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.OrderDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IOrderRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.OrderInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.OrderUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class OrderRepository(
    private val apiClient: ApiClient = ApiClient
) : IOrderRepository {

    override suspend fun GetOrderListAsync(): Result<List<OrderDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetOrderListAsync"
        )
    }

    override suspend fun GetOrderByIdAsync(
        orderId: Int
    ): Result<OrderUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetOrderByIdAsync",
            query = "orderId=$orderId"
        )
    }

    override suspend fun GetOrderByIdExtendedAsync(
        orderId: Int
    ): Result<OrderDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetOrderByIdExtendedAsync",
            query = "orderId=$orderId"
        )
    }

    override suspend fun InsertAsync(
        model: OrderInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: OrderUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        orderId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "orderId=$orderId"
        )
    }
}